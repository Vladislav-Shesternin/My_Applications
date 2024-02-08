package com.egyptian.rebirth.gremmy.actors.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.*
import com.egyptian.rebirth.gremmy.actors.Mask
import com.egyptian.rebirth.gremmy.manager.SpriteManager
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedGroup
import com.egyptian.rebirth.log
import com.egyptian.rebirth.toMS
import com.egyptian.rebirth.gremmy.util.Layout.SlotGroup as LSG

class SlotGroup: AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 6
        const val GLOW_COUNT = 6

        const val TIME_BETWEEN_SPIN_SLOT = 0.6f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.6f
        const val TIME_GLOW_IN = 0.33f
        const val TIME_GLOW_OUT = 0.33f
        const val TIME_BETWEEN_GLOW_IN = 0.15f
    }

    private val mask       = Mask()
    private val slotList   = List(SLOT_COUNT) { Slot() }
    private val glowList   = List(GLOW_COUNT) { Glow() }

    private var winNumber      = (2..5).shuffled().first()
    private var spinWinCounter = 0

    private val fillManager by lazy { SlotFillManager(slotList) }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.SLOT_GROUP.region))
        addGlowList()
        addMask()
    }

    private fun addGlowList() {
        var newX = LSG.glow.x

        glowList.onEach { glow ->
            addActor(glow)
            glow.apply {
                with(LSG.glow) {
                    setBounds(newX, y, w, h)
                    newX += w + hs
                }
            }
        }
    }

    private fun addMask() {
        addActor(mask)
        mask.apply {
            with(LSG.mask) { setBounds(x, y, w, h) }
            addSlotList()
        }
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
            delay(TIME_WAIT_AFTER_SHOW_WIN.toMS)
            hideWin()
        }?.winSlotItemSet

        continuation.complete(SpinResult(winSlotItemSet?.apply { resetWin() }))

    }.await()

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (2..5).shuffled().first()
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

    private suspend fun FillResult.showWin() {
        val completableList = List(intersectionList.size) { CompletableDeferred<Boolean>() }

        intersectionList.onEachIndexed { index, intersection ->
            CoroutineScope(Dispatchers.Main).launch {
                glowList[intersection.slotIndex].glowIn(intersection.rowIndex, TIME_GLOW_IN)
                completableList[index].complete(true)
            }
            delay(TIME_BETWEEN_GLOW_IN.toMS)
        }

        completableList.onEach { it.await() }
    }

    private fun hideWin() {
        glowList.onEach { CoroutineScope(Dispatchers.Main).launch { it.glowOutAll(TIME_GLOW_OUT) } }
    }

}