package com.mesga.moolahit.game.actors.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.*
import com.mesga.moolahit.game.actors.masks.normal.Mask
import com.mesga.moolahit.game.actors.sgoup.SGroup
import com.mesga.moolahit.game.manager.SpriteManager
import com.mesga.moolahit.game.screens.win
import com.mesga.moolahit.game.util.advanced.AdvancedGroup
import com.mesga.moolahit.log
import com.mesga.moolahit.toMS
import com.mesga.moolahit.game.util.Layout.SlotGroup as LSG

class SlotGroup: AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 4
        const val GLOW_COUNT = 4
        const val S_COUNT    = 3

        const val TIME_BETWEEN_SPIN_SLOT = 0.4f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.5f
        const val TIME_GLOW_IN = 0.5f
        const val TIME_GLOW_OUT = 0.3f
        const val TIME_BETWEEN_GLOW_IN = 0.2f
    }

    private val mask       = Mask()
    private val slotList   = List(SLOT_COUNT) { Slot() }
    private val glowList   = List(GLOW_COUNT) { Glow() }
    private val sList      = List(S_COUNT) { SGroup() }

    private var winNumber      = (1..4).shuffled().first()
    private var spinWinCounter = 0

    private val fillManager by lazy { SlotFillManager(slotList) }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.SLOT.region))
        addMask()
        addGlowList()
        addSList()
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

    private fun addSList() {
        var newX = LSG.s.x

        sList.onEach { s ->
            addActor(s)
            with(LSG.s) {
                s.setBounds(newX, y, w, h)
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

            win?.play(0.8f)

            showWin()
            delay(TIME_WAIT_AFTER_SHOW_WIN.toMS)
            hideWin()
        }?.winSlotItemSet

        continuation.complete(SpinResult(winSlotItemSet?.apply { resetWin() }))

    }.await()

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..4).shuffled().first()
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