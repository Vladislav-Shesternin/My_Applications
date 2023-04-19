package com.veldan.pharaohslots.manager.slot

import com.veldan.pharaohslots.actors.slot.*
import com.veldan.pharaohslots.utils.log
import com.veldan.pharaohslots.utils.toDelay
import kotlinx.coroutines.*
import kotlin.random.Random

class SlotManager(
    val slotList: List<Slot3Animated>,
) {

    companion object {
        // seconds
        const val TIME_BETWEEN_GO = Slot3Animated.TIME_GO / 2f
    }

    private var winNumber       = (1..4).random()
    private var miniGameNumber  = (4..6).random()
    private var superGameNumber = (6..10).random()

    private var winGoCounter       = 0
    private var miniGameGoCounter  = 0
    private var superGameGoCounter = 0

    private val fillSlotManager = FillSlot3Manager(slotList)

    private var bonus: Bonus?    = null
    private var isUseSlotItemAll = false
    private var extraFactor = 0f



    private fun fillSlots() {
        when {
            superGameGoCounter == superGameNumber -> {
                fillSlotManager.fill(FillStrategy.SUPER)
                bonus = Bonus.SUPER_GAME
            }
            miniGameGoCounter == miniGameNumber   -> {
                fillSlotManager.fill(FillStrategy.MINI)
                bonus = Bonus.MINI_GAME
            }
            winGoCounter == winNumber               -> {
                isUseSlotItemAll = Random.nextBoolean()
                if (isUseSlotItemAll) extraFactor += 2f
                fillSlotManager.fill(FillStrategy.WIN(isUseSlotItemAll))
            }
            else                                    -> {
                isUseSlotItemAll = Random.nextBoolean()
                fillSlotManager.fill(FillStrategy.MIX())
            }
        }
    }



    private fun resetWin() {
        winGoCounter = 0
        winNumber = (1..4).random()
    }

    private fun resetBonus() {
        if (winGoCounter == winNumber) resetWin()

        when (bonus) {
            Bonus.MINI_GAME -> {
                miniGameGoCounter = 0
                miniGameNumber = (4..6).random()
            }
            Bonus.SUPER_GAME -> {
                superGameGoCounter = 0
                superGameNumber = (6..10).random()

                if (miniGameGoCounter == miniGameNumber) {
                    miniGameGoCounter = 0
                    miniGameNumber = (4..6).random()
                }
            }
            else -> { }
        }
        bonus = null
    }

    private fun resetExtraCoefficient() {
        extraFactor = 0f
    }



    suspend fun spin() = coroutineScope {
        winGoCounter++
        miniGameGoCounter++
        superGameGoCounter++

        log("""
            
            winSpinCounter = $winGoCounter WIN_NUM = $winNumber
            miniGameSpinCounter = $miniGameGoCounter MINI_NUM = $miniGameNumber
            superGameSpinCounter = $superGameGoCounter SUPER_NUM = $superGameNumber
        """)

        fillSlots()

        slotList.onEach { slot ->
            launch { slot.go() }
            delay(TIME_BETWEEN_GO.toDelay)
        }
        delay((Slot3Animated.TIME_GO - TIME_BETWEEN_GO).toDelay)

        GoResult(
            fillResultList   = fillSlotManager.fillResultList?.apply { resetWin() },
            bonus            = bonus?.apply { resetBonus() },
            extraFactor      = extraFactor.also { if (it != 0f) resetExtraCoefficient() }
        )
    }

}