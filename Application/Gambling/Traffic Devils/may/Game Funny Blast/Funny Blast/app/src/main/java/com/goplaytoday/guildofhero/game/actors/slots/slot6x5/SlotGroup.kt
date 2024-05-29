package com.goplaytoday.guildofhero.game.actors.slots.slot6x5

import com.goplaytoday.guildofhero.game.actors.masks.Mask
import com.goplaytoday.guildofhero.game.actors.slots.FillStrategy
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedGroup
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen
import com.goplaytoday.guildofhero.game.utils.toMS
import com.goplaytoday.guildofhero.util.log
import kotlinx.coroutines.*
import com.goplaytoday.guildofhero.game.utils.Layout.SlotGroup as LSG

class SlotGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 6
        const val GLOW_COUNT = 6

        const val TIME_BETWEEN_SPIN_SLOT   = 0.25f
        const val TIME_SHOW_WIN            = 0.23f
        const val TIME_BETWEEN_SHOW_WIN    = 0.15f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.28f
    }

    private val mask       = Mask(screen, alphaHeight = 1000)
    private val slotList   = List(SLOT_COUNT) { Slot(screen) }
    private val glowList   = List(GLOW_COUNT) { Glow(screen) }

    private var winNumber   = (1..3).shuffled().first()
    private var spinCounter = 0

    private val slotItemContainer = SlotItemContainer(screen.game.commonAssets.items)
    private val slotFillManager   = SlotFillManager(slotList, glowList, slotItemContainer)

    private var isWin = false

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    override fun addActorsOnGroup() {
        addGlowList()
        addMask()
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
        spinCounter++
        logCounterAndWinNumber()
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

        glowList.onEach { it.show(TIME_SHOW_WIN, TIME_BETWEEN_SHOW_WIN) }
        delay(TIME_WAIT_AFTER_SHOW_WIN.toMS)
        glowList.onEach { it.hide(TIME_SHOW_WIN, TIME_BETWEEN_SHOW_WIN) }

        continuation.complete(isWin)

    }.await()

    private fun logCounterAndWinNumber() {
        log("spin: $spinCounter | win: $winNumber")
    }

    private fun fillSlots() {
        when (spinCounter) {
            winNumber -> {
                resetWin()
                slotFillManager.fill(FillStrategy.WIN)
                isWin = true
            }
            else      -> {
                slotFillManager.fill(FillStrategy.MIX)
                isWin = false
            }
        }
    }

    private fun resetWin() {
        spinCounter = 0
        winNumber   = (1..5).shuffled().first()
    }

}