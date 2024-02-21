package com.rcks.scaloi.game.actors.slot

import com.rcks.scaloi.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 15

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.ListRegion.ITEMS.regionList.last(), 55f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],1.1f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],2.2f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],3.3f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],4.4f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],5.5f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],6.6f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],7.7f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],8.8f),
        SlotItem(9, SpriteManager.ListRegion.ITEMS.regionList[8],9.9f),
        SlotItem(10, SpriteManager.ListRegion.ITEMS.regionList[9],10.7f),
        SlotItem(11, SpriteManager.ListRegion.ITEMS.regionList[10],11.9f),
        SlotItem(12, SpriteManager.ListRegion.ITEMS.regionList[11],12.9f),
        SlotItem(13, SpriteManager.ListRegion.ITEMS.regionList[12],13.9f),
        SlotItem(14, SpriteManager.ListRegion.ITEMS.regionList[13],14.9f),
    )

}