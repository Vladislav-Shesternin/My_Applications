package com.veldan.fantasticslots.actors.slot.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.fantasticslots.actors.slot.SlotItem
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.layout.getFigmaY
import com.veldan.fantasticslots.utils.toDelay
import kotlinx.coroutines.delay
import com.veldan.fantasticslots.utils.Slot as S
import com.veldan.fantasticslots.utils.SlotGroup as SG

class Slot : AbstractAdvancedGroup() {
    override val controller = SlotController(this)

    companion object {
        // seconds
        const val TIME_SPIN = 3f

        const val ITEM_INTERMEDIATE_COUNT = 90
        const val VISIBLE_SLOT_ITEM_COUNT = 5

        val START_Y = getFigmaY(S.START_Y, S.H, SG.MASK_GROUP_H)
        val END_Y   = getFigmaY(S.END_Y  , S.H, SG.MASK_GROUP_H)
    }

    private val slotItemImageWinList  = List(VISIBLE_SLOT_ITEM_COUNT) { Image() }
    private val slotItemImageFakeList = List(VISIBLE_SLOT_ITEM_COUNT) { Image() }

    var slotItemWinList = listOf<SlotItem>()
        set(value) {
            field = value
            value.onEachIndexed { index, slotItem -> slotItemImageWinList[index].drawable = TextureRegionDrawable(slotItem.texture) }
        }



    init {
        setSize(S.W, S.H)
        addActorsOnGroup()
    }


    private fun addActorsOnGroup() {
        addSlotItems()
    }



    private fun addSlotItems() {
        mutableListOf<Image>().apply {
            addAll(generateWinItems())
            addAll(generateIntermediateItems())
            addAll(generateFakeItems())
        }.onEach { addActorChain(it, ChainStyle.START_TOP_END_BOTTOM, 1, 0f, S.SLOT_ITEM_SPACE_VERTICAL) }
    }



    private fun generateWinItems() = slotItemImageWinList.apply { onEach { it.setUpItem() } }

    private fun generateIntermediateItems() = List(ITEM_INTERMEDIATE_COUNT) { Image().apply { setUpItem() } }

    private fun generateFakeItems() = slotItemImageFakeList.apply { onEach { it.setUpItem() } }



    private fun Image.setUpItem() {
        val tmpSlotItemList = SpriteManager.SlotItemSpriteList.SLOT_ITEM_LIST.dataList + SpriteManager.GameSprite.WILD.data + SpriteManager.GameSprite.SCATTER.data
        drawable = TextureRegionDrawable(tmpSlotItemList.random().texture)
        setSize(S.SLOT_ITEM_W, S.SLOT_ITEM_H)
    }



    fun reset() {
        clearActions()
        Gdx.app.postRunnable {
            slotItemImageWinList.onEachIndexed { index, image -> slotItemImageFakeList[index].drawable = image.drawable }
            setPosition(x, START_Y)
        }
    }



    suspend fun spin() {
        Gdx.app.postRunnable { addAction(Actions.moveTo(x, END_Y, TIME_SPIN, Interpolation.pow4)) }
        delay(TIME_SPIN.toDelay)
    }

}