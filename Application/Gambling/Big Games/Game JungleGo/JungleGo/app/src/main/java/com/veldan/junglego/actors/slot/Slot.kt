package com.veldan.junglego.actors.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.utils.Slot
import com.veldan.junglego.utils.SlotGroup
import com.veldan.junglego.utils.getFigmaY
import kotlinx.coroutines.delay

class Slot: AdvancedGroup() {

    companion object{
        private const val ITEM_INTERMEDIATE_COUNT = 92

        const val DELAY_SPIN = 5100L
        const val DELAY_SLOW_SPIN = 10100L

        const val VISIBLE_SLOT_ITEM_COUNT = 4
    }

    private val START_Y by lazy { getFigmaY(Slot.START_Y, height, SlotGroup.H) }
    private val END_Y by lazy { getFigmaY(Slot.END_Y, height, SlotGroup.H) }

    private val slotItemsWinList = List(VISIBLE_SLOT_ITEM_COUNT) { Image() }
    private val slotItemsFakeList = List(VISIBLE_SLOT_ITEM_COUNT) { Image() }

    private val randomSlotItemList = SpriteManager.SlotItemSpriteList.SLOT_ITEM_LIST.textureDataList

    var publicSlotItemWinList = listOf<SlotItem>()
        set(value) {
            field = value
            field.onEachIndexed { index, slotItem -> slotItemsWinList[index].drawable = TextureRegionDrawable(slotItem.item) }
        }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup(){
        addSlots()
    }



    private fun addSlots(){
        mutableListOf<Image>().apply {
            addAll(generateWinItems())
            addAll(generateIntermediateItems())
            addAll(generateFakeItems())
        }.onEach { addActorChain(it, ChainStyle.START_TOP_END_BOTTOM, 1, 0f, Slot.SLOT_ITEM_SPACE_VERTICAL) }
    }



    private fun generateWinItems() = slotItemsWinList.apply { onEach { it.setUpItem() } }

    private fun generateIntermediateItems() = List(ITEM_INTERMEDIATE_COUNT) { Image().apply { setUpItem() } }

    private fun generateFakeItems() = slotItemsFakeList.apply { onEach { it.setUpItem() } }



    private fun Image.setUpItem(){
        drawable = TextureRegionDrawable(randomSlotItemList.random().texture)
        setSize(Slot.SLOT_ITEM_W, Slot.SLOT_ITEM_H)
    }



    private fun updateAndToStart() {
        Gdx.app.postRunnable {
            slotItemsWinList.onEachIndexed { index, image -> slotItemsFakeList[index].drawable = image.drawable }
            setPosition(x, START_Y)
        }
    }



    suspend fun spin() {
        Gdx.app.postRunnable { addAction(Actions.moveTo(x, END_Y, 5f, Interpolation.pow4)) }
        delay(DELAY_SPIN)
        updateAndToStart()
    }

    suspend fun slowSpin() {
        addAction(Actions.moveTo(x, END_Y, 10f, Interpolation.exp10Out))
        delay(DELAY_SLOW_SPIN)
        updateAndToStart()
    }

}