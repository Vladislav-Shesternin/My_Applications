package com.favsport.slots.actors.slot

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.favsport.slots.advanced.AdvancedGroup
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.utils.*
import com.favsport.slots.utils.slot.SlotItem
import kotlinx.coroutines.delay

class Slot: AdvancedGroup() {

    companion object{
        private const val ITEM_INTERMEDIATE_COUNT = 94
        const val DELAY_SPIN = 5100L
        const val DELAY_SLOW_SPIN = 10100L
    }

    private val itemsWin = List(3) { Image() }
    private val itemsFake = List(3) { Image() }

    private val listRandomItem = SpriteManager.GameSpriteList.ITEM_LIST.textureDataList + SpriteManager.GameSprite.ALL.textureData

    var listItemWin = mutableListOf<SlotItem>()




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
        }.onEach { addActorChain(it, ChainStyle.StartTop_EndBottom, 1, 0f, SLOT_ITEM_SPACE_VERTICAL) }
    }



    private fun generateWinItems() = itemsWin.apply { onEach { it.setUpItem() } }

    private fun generateIntermediateItems() = List(ITEM_INTERMEDIATE_COUNT) { Image().apply { setUpItem() } }

    private fun generateFakeItems() = itemsFake.apply { onEach { it.setUpItem() } }



    private fun Image.setUpItem(){
        drawable = TextureRegionDrawable(listRandomItem.random().texture)
        setSize(SLOT_ITEM_W, SLOT_ITEM_H)
    }



    private fun replaceFakes() {
        itemsWin.onEachIndexed{ index, image ->
            itemsFake[index].drawable = image.drawable
        }
        val newY = getFigmaY(SLOT_START_Y, height)
        setPosition(x, newY)
    }



    fun setWin(listSlotItems: List<SlotItem>) {
        listItemWin.clear()
        listSlotItems.onEachIndexed { index, itemSlot ->
            listItemWin.add(itemSlot)
            itemsWin[index].drawable = TextureRegionDrawable(itemSlot.item)
        }
    }



    suspend fun spin() {
        val newY = getFigmaY(SLOT_END_Y, height)
        addAction(Actions.moveTo(x, newY, 5f, Interpolation.pow4))
        delay(DELAY_SPIN)
        replaceFakes()
    }

    suspend fun slowSpin() {
        val newY = getFigmaY(SLOT_END_Y, height)
        addAction(Actions.moveTo(x, newY, 10f, Interpolation.exp10Out))
        delay(DELAY_SLOW_SPIN)
        replaceFakes()
    }

}