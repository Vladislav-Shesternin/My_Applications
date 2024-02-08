package com.gen.bettermeditatio.game.actors.slot

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.*
import com.gen.bettermeditatio.game.actors.masks.normal.Mask
import com.gen.bettermeditatio.game.manager.SpriteManager
import com.gen.bettermeditatio.game.util.advanced.AdvancedGroup
import com.gen.bettermeditatio.log
import com.gen.bettermeditatio.toMS
import com.gen.bettermeditatio.game.util.Layout.SlotGroup as LSG

class SlotGroup: AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 3

        const val TIME_BETWEEN_SPIN_SLOT = 1f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.5f
        const val TIME_GLOW_IN = 1f
        const val TIME_GLOW_OUT = 1f
    }

    private val mask       = Mask(SpriteManager.GameRegion.MASK.region)
    private val slotList   = List(SLOT_COUNT) { Slot() }
    private val glow       = Image(SpriteManager.GameRegion.GLOW.region)

    private var winNumber      = (1..5).shuffled().first()
    private var spinWinCounter = 0

    private val fillManager by lazy { SlotFillManager(slotList) }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addMask()
        addGlow()
    }

    private fun addGlow() {
        addActor(glow)
        glow.apply {
            addAction(Actions.alpha(0f))
            with(LSG.glow) { setBounds(x, y, w, h) }
        }
    }

    private fun addMask() {
        addAndFillActors(Image(SpriteManager.GameRegion.SLOT.region), mask)
        mask.addSlotList()
    }

    private fun AdvancedGroup.addSlotList() {
        var newX = LSG.slot.x

        slotList.onEach { slot ->
            addActor(slot)
            with(LSG.slot) {
                slot.setBounds(newX, y, w, h)
                newX += w + hs
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    suspend fun spin() = CompletableDeferred<SpinResult>().also { continuation ->
        spinWinCounter++
        logCounterWin()
        fillSlots()

        val completableList = List(SLOT_COUNT) { CompletableDeferred<Boolean>() }

        slotList.onEachIndexed { index, slot ->
            CoroutineScope(Dispatchers.Main).launch {
                slot.spin()
                completableList[index].complete(true)
            }
            delay(TIME_BETWEEN_SPIN_SLOT.toMS)
        }
        completableList.onEach { it.await() }

        val winSlotItemSet = fillManager.winFillResult?.apply {
            showWin()
        }?.winSlotItemSet

        continuation.complete(SpinResult(winSlotItemSet?.apply { resetWin() }))

    }.await()

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..5).shuffled().first()
    }

    private fun logCounterWin() {
        log("spinWinCounter = $spinWinCounter | winNumber = $winNumber")
    }

    private fun fillSlots() {
        when (spinWinCounter) {
            winNumber -> fillManager.fill(FillStrategy.WIN)
            else      -> fillManager.fill(FillStrategy.MIX)
        }
    }

    private fun showWin() {
        glow.addAction(Actions.sequence(
            Actions.fadeIn(TIME_GLOW_IN),
            Actions.delay(TIME_WAIT_AFTER_SHOW_WIN),
            Actions.fadeOut(TIME_GLOW_OUT),
        ))
    }

}