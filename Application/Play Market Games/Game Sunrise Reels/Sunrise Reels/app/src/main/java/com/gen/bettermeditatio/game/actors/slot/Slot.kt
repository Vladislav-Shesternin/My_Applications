package com.gen.bettermeditatio.game.actors.slot

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.gen.bettermeditatio.game.util.advanced.AdvancedGroup
import com.gen.bettermeditatio.log
import kotlinx.coroutines.CompletableDeferred
import com.gen.bettermeditatio.game.util.Layout.Slot as LS

class Slot: AdvancedGroup() {

    companion object {
        const val SLOT_ITEM_VISIBLE_COUNT      = 1
        const val SLOT_ITEM_INTERMEDIATE_COUNT = 8

        const val TIME_SPIN_SLOT = 3f
    }

    private val intermediateSlotItemImageList = List(SLOT_ITEM_INTERMEDIATE_COUNT) { Image() }
    private val winSlotItemImageList          = List(SLOT_ITEM_VISIBLE_COUNT) { Image() }
    private val fakeSlotItemImageList         = List(SLOT_ITEM_VISIBLE_COUNT) { Image() }

    var slotItemWinList = listOf<SlotItem>()
        set(value) {
            field = value
            value.onEachIndexed { index, slotItem -> winSlotItemImageList[index].drawable = TextureRegionDrawable(slotItem.region) }
        }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addSlotItemList()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addSlotItemList() {
        var newY = LS.slot.y
        (fakeSlotItemImageList.reversed() + intermediateSlotItemImageList + winSlotItemImageList.reversed()).onEachIndexed { index, slotItemImage ->
            addActor(slotItemImage)
            slotItemImage.apply {
                drawable = TextureRegionDrawable((SlotItemContainer.list + SlotItemContainer.wild).shuffled().first().region)
                with(LS.slot) {
                    setBounds(x, newY, w, h)
                    newY += h + vs
                }

                //if (index == 2) newY += 45f
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun reset() {
        winSlotItemImageList.onEachIndexed { index, image -> fakeSlotItemImageList[index].drawable = image.drawable }
        y = LS.startY
    }



    suspend fun spin() = CompletableDeferred<Boolean>().also { continuation ->
        addAction(Actions.sequence(
            Actions.moveTo(x, LS.endY, TIME_SPIN_SLOT, Interpolation.circle),
            Actions.run {
                reset()
                continuation.complete(true)
            }
        ))
    }.await()
}