package com.tmesfo.frtunes.game.actors.slot.slotGroup

import kotlinx.coroutines.*
import com.tmesfo.frtunes.game.actors.slot.util.*
import com.tmesfo.frtunes.game.manager.assets.util.SoundUtil
import com.tmesfo.frtunes.game.manager.assets.util.playAdvanced
import com.tmesfo.frtunes.game.utils.controller.GroupController
import com.tmesfo.frtunes.util.log
import com.tmesfo.frtunes.util.toDelay

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        const val SLOT_COUNT = 3
        const val GLOW_COUNT = SLOT_COUNT

        // seconds
        const val TIME_WAIT_AFTER_SHOW_WIN = 2f
        const val TIME_BETWEEN_SPIN        = 0.3f
        const val TIME_SHOW_WIN            = 0.5f
        const val TIME_HIDE_WIN            = 0.5f
    }

    private var winNumber = (1..5).random()

    private var spinWinCounter = 0

    private val fillManager by lazy { FillManager(group.slotList) }



    private fun fillSlots() {
        when (spinWinCounter) {
            winNumber -> {
                fillManager.fill(FillStrategy.WIN)
            }
            else      -> {
                fillManager.fill(FillStrategy.MIX)
            }
        }
    }

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..5).random()
    }

    private fun logCounter() {
        log("winSpinCounter = $spinWinCounter WIN_NUM = $winNumber")
    }

    private suspend fun FillResult.showWin() {
        val completableList = List(intersectionList.size) { CompletableDeferred<Boolean>() }

        SoundUtil.WIN.playAdvanced()
        intersectionList.onEachIndexed { index, intersection ->
            CoroutineScope(Dispatchers.Default).launch {
                group.glowList[intersection.slotIndex].controller.glowIn(intersection.rowIndex, TIME_SHOW_WIN)
                completableList[index].complete(true)
            }
        }

        completableList.onEach { it.await() }
    }



    suspend fun spin() = CompletableDeferred<SpinResult>().also { continuation ->
        spinWinCounter++
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
            group.glowList.onEach { CoroutineScope(Dispatchers.Default).launch { it.controller.glowOutAll(TIME_HIDE_WIN) } }
        }?.winSlotItemList?.toSet()

        continuation.complete(SpinResult(winSlotItemSet = winSlotItemSet?.apply { resetWin() }))

    }.await()

}