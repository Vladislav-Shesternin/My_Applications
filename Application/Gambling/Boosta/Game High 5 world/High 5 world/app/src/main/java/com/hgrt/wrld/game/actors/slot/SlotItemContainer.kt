package com.hgrt.wrld.game.actors.slot

import com.hgrt.wrld.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 50f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],2.1f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],3.2f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],2.3f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],4.4f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],2.5f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],5.6f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],2.7f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],6.8f),
        SlotItem(9, SpriteManager.ListRegion.ITEMS.regionList[8],2.9f),
    )

}