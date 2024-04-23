package com.tdapps.test.game.actors.slots.slot4x3

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tdapps.test.game.actors.slots.SlotItem

class SlotItemContainer(private val itemRegions: List<TextureRegion>) {
//    companion object {
//        const val SLOT_ITEM_WILD_ID = 14
//    }

    //val wild = SlotItem(SLOT_ITEM_WILD_ID, itemRegions[13])
    val list = List(3) { SlotItem(it.inc(), itemRegions[it]) }

}