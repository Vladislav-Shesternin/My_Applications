package com.egyptian.rebirth.gremmy.actors.slot

import com.egyptian.rebirth.gremmy.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 9

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 11f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],4.1f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],4.6f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],4.3f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],5.4f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],5.6f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],5.6f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],9.7f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],9.6f),
    )

}