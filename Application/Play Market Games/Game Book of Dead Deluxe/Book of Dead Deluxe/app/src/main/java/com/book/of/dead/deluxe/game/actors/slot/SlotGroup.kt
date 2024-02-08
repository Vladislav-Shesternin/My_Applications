package com.book.of.dead.deluxe.game.actors.slot

import kotlinx.coroutines.*
import com.book.of.dead.deluxe.game.actors.masks.normal.Mask
import com.book.of.dead.deluxe.game.util.advanced.AdvancedGroup
import com.book.of.dead.deluxe.log
import com.book.of.dead.deluxe.toMS
import com.book.of.dead.deluxe.game.util.Layout.SlotGroup as LSG

class SlotGroup: AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 5
        const val GLOW_COUNT = 5

        const val TIME_BETWEEN_SPIN_SLOT = 0.5f
        const val TIME_WAIT_AFTER_SHOW_WIN = 1f
        const val TIME_GLOW_IN = 0.5f
        const val TIME_GLOW_OUT = 0.5f
        const val TIME_BETWEEN_GLOW_IN = 0.5f
    }

    private val mask       = Mask()
    private val slotList   = List(SLOT_COUNT) { Slot() }
    private val glowList   = List(GLOW_COUNT) { Glow() }

    private var winNumber      = (1..5).shuffled().first()
    private var spinWinCounter = 0

    private val fillManager by lazy { SlotFillManager(slotList) }

    var bonusCount = 0



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
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
        addAndFillActor(mask)
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
        bonusCount = 0
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

        bonusCount = slotList.map { it.slotItemWinList }.flatten().map { it.id }.count { it == SlotItemContainer.SLOT_ITEM_BONUS_WILD_ID }

        log("count = $bonusCount")

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