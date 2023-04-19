package com.vbt.game.sptr.game.actors.slot

import com.vbt.game.sptr.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 10f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],3.1f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],3.6f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],3.3f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],3.4f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],2.6f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],3.6f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],2.7f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],3.6f),
    )

}