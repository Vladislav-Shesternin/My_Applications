package com.veldan.fantasticslots.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.fantasticslots.actors.slot.Bonus
import com.veldan.fantasticslots.actors.slot.FillStrategy
import com.veldan.fantasticslots.actors.slot.SpinResult
import com.veldan.fantasticslots.actors.slot.slot.Slot
import com.veldan.fantasticslots.actors.slot.slotGroup.util.Check
import com.veldan.fantasticslots.actors.slot.slotGroup.util.Fill
import com.veldan.fantasticslots.screens.game.GameScreenController
import com.veldan.fantasticslots.screens.game.Tutorial
import com.veldan.fantasticslots.utils.controller.GroupController
import com.veldan.fantasticslots.utils.log
import com.veldan.fantasticslots.utils.toDelay
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        // seconds
        const val TIME_BETWEEN_SPIN                      = Slot.TIME_SPIN / 5
        const val TIME_WAIT_AFTER_GLOW_IN                = 2f

        const val SLOT_COUNT = 5
        const val GLOW_COUNT = SLOT_COUNT
    }

    private var winNumber       = (1..5).random()
    private var superGameNumber = (8..10).random()

    private var winGoCounter       = 0
    private var superGameGoCounter = 0

    private val fill by lazy { Fill(group.slotList) }

    private var bonus: Bonus? = null



    private fun fillSlots() {
        when {
            superGameGoCounter == superGameNumber -> {
                fill.fill(FillStrategy.SUPER)
                bonus = Bonus.SUPER_GAME
            }
            winGoCounter == winNumber               -> {
                fill.fill(FillStrategy.WIN)
            }
            else                                    -> {
                fill.fill(FillStrategy.RANDOM)
            }
        }
    }

    private fun resetWin() {
        winGoCounter = 0
        winNumber = (1..5).random()
    }

    private fun resetBonus() {
        if (winGoCounter == winNumber) resetWin()

        when (bonus) {
            Bonus.SUPER_GAME -> {
                superGameGoCounter = 0
                superGameNumber = (8..10).random()
            }
            else             -> {}
        }
        bonus = null
    }

    private fun replaceSuperGameSlotItemWithWild() {
        GameScreenController.superGameSlotItem?.let { superSlotItem ->

            group.slotList.onEach { slot ->
                with(slot){ slotItemWinList = slotItemWinList.toMutableList().apply {
                    onEachIndexed { index, slotItem -> if (slotItem.id == superSlotItem.id) { set(index, Fill.slotItemWild) } }
                } }
            }

        }
    }



    suspend fun spin() = coroutineScope {
        winGoCounter++
        superGameGoCounter++

        log("""
            
            winSpinCounter = $winGoCounter WIN_NUM = $winNumber
            superGameSpinCounter = $superGameGoCounter SUPER_NUM = $superGameNumber
        """)

        fillSlots()

        group.slotList.onEach { slot ->
            launch { slot.spin() }
            delay(TIME_BETWEEN_SPIN.toDelay)
        }
        delay((Slot.TIME_SPIN - TIME_BETWEEN_SPIN).toDelay)

        replaceSuperGameSlotItemWithWild()

        group.slotList.onEach { slot -> slot.reset() }

        val checkResult = Check(group.slotList).checkWin()

        val winSlotItemSet = if (checkResult.checkSlotList.isNotEmpty()) {
            checkResult.checkSlotList.onEach { checkSlot -> checkSlot.rowList.onEach { row ->
                group.glowList[checkSlot.index].glowIn(row)
            } }
            delay(TIME_WAIT_AFTER_GLOW_IN.toDelay)
            group.glowList.onEach { it.glowOutAll() }

            checkResult.slotItem
        } else null

        SpinResult(
            winSlotItemSet = winSlotItemSet?.apply { resetWin() },
            bonus          = bonus?.apply { resetBonus() },
        )
    }

    fun showTutorialPanel() {
        with(group) {
            children.onEach { actor -> actor.addAction(Actions.fadeOut(Tutorial.TIME_HIDE)) }
            panelTutorialImage.addAction(Actions.fadeIn(Tutorial.TIME_SHOW))
        }
    }

    fun showGlowTutorial() {
        group.glowTutorialImage.addAction(Actions.fadeIn(Tutorial.TIME_SHOW))
    }

    fun hideTutorial() {
        with(group) {
            children.onEach { actor -> actor.addAction(Actions.fadeIn(Tutorial.TIME_SHOW)) }
            panelTutorialImage.addAction(Actions.sequence(
                Actions.fadeOut(Tutorial.TIME_HIDE),
                Actions.removeActor()
            ))
            glowTutorialImage.addAction(Actions.sequence(
                Actions.fadeOut(Tutorial.TIME_HIDE),
                Actions.removeActor()
            ))
        }
    }

}