package mst.mysteryof.egyptest.game.actors.slots.slot4x3

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mst.mysteryof.egyptest.game.actors.masks.Mask
import mst.mysteryof.egyptest.game.actors.slots.FillStrategy
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedGroup
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedScreen
import mst.mysteryof.egyptest.game.utils.toMS
import mst.mysteryof.egyptest.util.log
import mst.mysteryof.egyptest.game.utils.Layout.SlotGroup as LSG

class SlotGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 4
        const val GLOW_COUNT = 4

        const val TIME_BETWEEN_SPIN_SLOT   = 0.3f
        const val TIME_SHOW_WIN            = 0.25f
        const val TIME_BETWEEN_SHOW_WIN    = 0.3f
        const val TIME_WAIT_AFTER_SHOW_WIN = 0.35f
    }

    private val mask       = Mask(screen, screen.game.spriteUtil.MASK, alphaHeight = 2000)
    private val slotList   = List(SLOT_COUNT) { Slot(screen) }
    private val glowList   = List(GLOW_COUNT) { Glow(screen) }

    private var winNumber   = (1..3).shuffled().first()
    private var spinCounter = 0

    private val slotItemContainer = SlotItemContainer(screen.game.spriteUtil.items)
    private val slotFillManager   = SlotFillManager(slotList, glowList, slotItemContainer)

    private var isWin = false

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.SLOGROUP))
        addMask()
        addGlowList()
    }

    private fun addMask() {
        addActor(mask)
        mask.setBounds(27f, 38f, 852f, 541f)
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