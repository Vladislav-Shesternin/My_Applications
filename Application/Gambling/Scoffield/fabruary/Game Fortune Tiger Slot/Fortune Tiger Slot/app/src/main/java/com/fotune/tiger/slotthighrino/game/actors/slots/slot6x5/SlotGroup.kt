package com.fotune.tiger.slotthighrino.game.actors.slots.slot6x5

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.fotune.tiger.slotthighrino.game.actors.masks.Mask
import com.fotune.tiger.slotthighrino.game.actors.slots.FillStrategy
import com.fotune.tiger.slotthighrino.game.utils.advanced.AdvancedGroup
import com.fotune.tiger.slotthighrino.game.utils.advanced.AdvancedScreen
import com.fotune.tiger.slotthighrino.game.utils.runGDX
import com.fotune.tiger.slotthighrino.game.utils.toMS
import com.fotune.tiger.slotthighrino.util.log
import com.fotune.tiger.slotthighrino.game.utils.Layout.SlotGroup as LSG

class SlotGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 3
        const val GLOW_COUNT = 3

        const val TIME_BETWEEN_SPIN_SLOT   = 0.15f
        const val TIME_SHOW_WIN            = 0.3f
        const val TIME_BETWEEN_SHOW_WIN    = 0.25f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.3f
    }

    private val mask       = Mask(screen, alphaHeight = 800)
    private val slotList   = List(SLOT_COUNT) { Slot(screen) }
    private val glowList   = List(GLOW_COUNT) { Glow(screen) }

    private var winNumber   = (1..5).shuffled().first()
    private var spinCounter = 0

    private val slotItemContainer = SlotItemContainer(screen.game.allAssets.listTiger)
    private val slotFillManager   = SlotFillManager(slotList, glowList, slotItemContainer)

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    override fun addActorsOnGroup() {
        addMask()
        addGlowList()
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

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    suspend fun spin() = CompletableDeferred<Boolean>().also { continuation ->
        isWin = false

        spinCounter++
        logCounterAndWinNumber()
        fillSlots()

        val completableList = List(SLOT_COUNT) { CompletableDeferred<Boolean>() }

        slotList.onEachIndexed { index, slot ->
            runGDX { screen.game.soundUtil.apply { play(roll, volumeLevel * 0.3f) } }
            CoroutineScope(Dispatchers.Main).launch {
                slot.spin()
                completableList[index].complete(true)
            }
            delay(TIME_BETWEEN_SPIN_SLOT.toMS)
        }
        completableList.onEach { it.await() }

        glowList.onEach { it.show(TIME_SHOW_WIN, TIME_BETWEEN_SHOW_WIN) }
        delay(TIME_WAIT_AFTER_SHOW_WIN.toMS)
        glowList.onEach { it.hide(TIME_SHOW_WIN, TIME_BETWEEN_SHOW_WIN) }

        continuation.complete(isWin)

    }.await()

    private fun logCounterAndWinNumber() {
        log("spin: $spinCounter | win: $winNumber")
    }

    private var isWin = false

    private fun fillSlots() {
        when (spinCounter) {
            winNumber -> {
                isWin = true
                resetWin()
                slotFillManager.fill(FillStrategy.WIN)
            }
            else      -> {
                slotFillManager.fill(FillStrategy.MIX)
            }
        }
    }

    private fun resetWin() {
        spinCounter = 0
        winNumber   = (1..5).shuffled().first()
    }

}