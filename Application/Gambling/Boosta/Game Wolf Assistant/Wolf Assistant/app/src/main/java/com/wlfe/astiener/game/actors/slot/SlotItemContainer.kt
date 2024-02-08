package com.wlfe.astiener.game.actors.slot

import com.wlfe.astiener.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 10f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],2f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],3f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],4f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],5f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],6f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],7f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],8f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],9f),
    )

}