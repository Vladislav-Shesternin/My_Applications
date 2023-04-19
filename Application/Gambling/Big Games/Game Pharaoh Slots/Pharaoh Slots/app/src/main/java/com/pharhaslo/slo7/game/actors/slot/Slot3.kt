package com.pharhaslo.slo7.game.actors.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.utils.setBoundsFigmaY
import com.pharhaslo.slo7.game.utils.Slot3 as S3

class Slot3: AdvancedGroup() {

    companion object{
        const val SLOT_ITEM_COUNT = 3
    }

    private val slotItemImageList = List(SLOT_ITEM_COUNT) { Image() }


    init {
        setSize(S3.W, S3.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup(){
        addSlotItemList()
    }


    private fun addSlotItemList() {
        slotItemImageList.onEachIndexed { index, image ->
            val newY = (S3.SLOT_ITEM_H + S3.SLOT_ITEM_SPACE_VERTICAL) * index
            image.setBoundsFigmaY(0f, newY, S3.SLOT_ITEM_W, S3.SLOT_ITEM_H, S3.H)
            addActor(image)
        }
    }



    fun setSlotItemList(slotItemList: List<SlotItem>) {
        if (slotItemList.size > 3) throw Exception("slotItemList.size > 3")

        slotItemList.onEachIndexed { index, slotItem ->
            slotItemImageList[index].drawable = TextureRegionDrawable(slotItem.texture)
        }
    }





}