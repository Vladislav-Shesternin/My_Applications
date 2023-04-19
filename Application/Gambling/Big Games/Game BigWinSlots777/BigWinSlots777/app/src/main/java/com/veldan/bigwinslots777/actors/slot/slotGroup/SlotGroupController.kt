package com.veldan.bigwinslots777.actors.slot.slotGroup

import com.veldan.bigwinslots777.actors.miniGameGroup.MiniGameGroup
import com.veldan.bigwinslots777.actors.slot.util.*
import com.veldan.bigwinslots777.manager.assets.util.SoundUtil
import com.veldan.bigwinslots777.manager.assets.util.playAdvanced
import com.veldan.bigwinslots777.screens.game.GameScreenController
import com.veldan.bigwinslots777.utils.controller.GroupController
import com.veldan.bigwinslots777.utils.log
import com.veldan.bigwinslots777.utils.probability
import com.veldan.bigwinslots777.utils.toDelay
import kotlinx.coroutines.*

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        const val SLOT_COUNT = 4
        const val GLOW_COUNT = SLOT_COUNT

        // seconds
        const val TIME_WAIT_AFTER_SHOW_WIN = 2f
        const val TIME_BETWEEN_SPIN        = 0.3f
        const val TIME_SHOW_WIN            = 0.5f
        const val TIME_HIDE_WIN            = 0.5f
    }

    private var winNumber       = (1..5).random()
    private var miniGameNumber  = (6..10).random()
    private var superGameNumber = (11..15).random()

    private var spinWinCounter       = 0
    private var spinMiniGameCounter  = 0
    private var spinSuperGameCounter = 0

    private val fillManager by lazy { FillManager(group.slotList) }

    private var bonus: Bonus? = null



    private fun fillSlots() {
        when {
            MiniGameGroup.isCheckWild.value         -> {
                if (probability(55)) fillManager.fill(FillStrategy.MINI_WILD_WIN)
                else fillManager.fill(FillStrategy.MINI_WILD_FAIL)
            }
            GameScreenController.numberWild > 0     -> {
                if (probability(55)) fillManager.fill(FillStrategy.SUPER_WILD_WIN)
                else fillManager.fill(FillStrategy.SUPER_WILD_FAIL)
            }
            spinSuperGameCounter == superGameNumber -> {
                fillManager.fill(FillStrategy.SUPER)
                bonus = Bonus.SUPER_GAME
            }
            spinMiniGameCounter == miniGameNumber   -> {
                fillManager.fill(FillStrategy.MINI)
                bonus = Bonus.MINI_GAME
            }
            spinWinCounter == winNumber             -> {
                fillManager.fill(FillStrategy.WIN)
            }
            else                                    -> {
                fillManager.fill(FillStrategy.MIX)
            }
        }
    }

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..5).random()
    }

    private fun resetBonus() {
        fun resetMiniGame() {
            spinMiniGameCounter = 0
            miniGameNumber      = (6..10).random()
        }
        fun resetSuperGame() {
            spinSuperGameCounter = 0
            superGameNumber      = (11..15).random()
        }

        if (spinWinCounter == winNumber) resetWin()
        if (spinMiniGameCounter == miniGameNumber) resetMiniGame()
        if (spinSuperGameCounter == superGameNumber) resetSuperGame()

        bonus = null
    }

    private fun logCounter() {
        log("""
            
            winSpinCounter = $spinWinCounter WIN_NUM = $winNumber
            miniGameSpinCounter = $spinMiniGameCounter MINI_NUM = $miniGameNumber
            superGameSpinCounter = $spinSuperGameCounter SUPER_NUM = $superGameNumber
        """)
    }

    private suspend fun FillResult.showWin() {
        val completableList = List(intersectionList.size) { CompletableDeferred<Boolean>() }

        SoundUtil.WIN.playAdvanced()
        intersectionList.onEachIndexed { index, intersection ->
            CoroutineScope(Dispatchers.Default).launch {
                group.glowList[intersection.slotIndex].controller.glowIn(intersection.rowIndex, TIME_SHOW_WIN)
                completableList[index].complete(true)
                cancel()
            }
        }

        completableList.onEach { it.await() }
    }



    suspend fun spin() = CompletableDeferred<SpinResult>().also { continuation ->
        if (GameScreenController.numberWild <= 0 && MiniGameGroup.isCheckWild.value.not()) {
            spinWinCounter++
        }
        if (GameScreenController.numberSpin == -1 && MiniGameGroup.isCheckWild.value.not()) {
            spinMiniGameCounter++
            spinSuperGameCounter++
        }

        logCounter()
        fillSlots()

        val completableSpinList = List(SLOT_COUNT) { CompletableDeferred<Boolean>() }
        group.slotList.onEachIndexed { slotIndex, slot ->
            CoroutineScope(Dispatchers.Default).launch {
                slot.controller.spin()
                completableSpinList[slotIndex].complete(true)
                cancel()
            }
            delay(TIME_BETWEEN_SPIN.toDelay)
        }
        completableSpinList.onEach { it.await() }

        val winSlotItemSet = fillManager.winFillResult?.apply {
            showWin()
            delay(TIME_WAIT_AFTER_SHOW_WIN.toDelay)
            group.glowList.onEach { CoroutineScope(Dispatchers.Default).launch {
                it.controller.glowOutAll(TIME_HIDE_WIN)
                cancel()
            } }
        }?.winSlotItemList?.toSet()

        continuation.complete(SpinResult(
            winSlotItemSet = winSlotItemSet?.apply { resetWin() },
            bonus = bonus?.apply { resetBonus() }
        ))

    }.await()

}