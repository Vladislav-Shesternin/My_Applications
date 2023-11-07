package mst.mysteryof.antientegyptua.game.actors.slots.slot3x3

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import kotlinx.coroutines.CompletableDeferred
import mst.mysteryof.antientegyptua.game.actors.slots.SlotItem
import mst.mysteryof.antientegyptua.game.utils.Layout
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedGroup
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedScreen
import mst.mysteryof.antientegyptua.game.utils.runGDX
import mst.mysteryof.antientegyptua.game.utils.Layout.Slot as LS

class Slot(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val SLOT_ITEM_VISIBLE_COUNT_WIN  = 3
        const val SLOT_ITEM_VISIBLE_COUNT_FAKE = 3
        const val SLOT_ITEM_INTERMEDIATE_COUNT = 35

        const val TIME_SPIN_SLOT = 3.2f
    }

    private val intermediateSlotItemImageList = List(SLOT_ITEM_INTERMEDIATE_COUNT) { Image() }
    private val winSlotItemImageList          = List(SLOT_ITEM_VISIBLE_COUNT_WIN) { Image() }
    private val fakeSlotItemImageList         = List(SLOT_ITEM_VISIBLE_COUNT_FAKE) { Image() }

    var listSlotItemWin = listOf<SlotItem>()
        set(value) {
            field = value
            value.onEachIndexed { index, slotItem -> winSlotItemImageList[index].drawable = TextureRegionDrawable(slotItem.region) }
        }

    override fun addActorsOnGroup() {
        addSlotItemList()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addSlotItemList() {
        var newY = LS.slot.y
        (fakeSlotItemImageList.reversed() + intermediateSlotItemImageList + winSlotItemImageList.reversed()).onEach { slotItemImage ->
            addActor(slotItemImage)
            slotItemImage.apply {
                drawable = TextureRegionDrawable(screen.game.spriteUtil.items.random())
                with(LS.slot) {
                    setBounds(x, newY, w, h)
                    newY += h + vs
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun reset() {
        winSlotItemImageList.onEachIndexed { index, image -> fakeSlotItemImageList[index].drawable = image.drawable }
        y = Layout.SlotGroup.slot.y
    }

    suspend fun spin() = CompletableDeferred<Boolean>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.moveTo(x, LS.endY , TIME_SPIN_SLOT, Interpolation.sine),
                Actions.run {
                    reset()
                    continuation.complete(true)
                }
            ))
        }
    }.await()
}