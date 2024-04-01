package com.aztec.firevoll.game.actors.slot

import com.aztec.firevoll.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 150f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],20.1f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],30.6f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],40.3f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],50.4f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],60.6f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],70.6f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],80.7f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],90.6f),
        SlotItem(9, SpriteManager.ListRegion.ITEMS.regionList[8],100.9f),
    )

}