package com.mesga.moolahit.game.actors.slot

import com.mesga.moolahit.game.manager.SpriteManager

object SlotItemContainer {

//    const val SLOT_ITEM_WILD_ID = 9

//    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 11f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],1.5f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],2.5f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],3.5f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],4.5f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],5.5f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],6.5f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],7.5f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],8.5f),
        SlotItem(9, SpriteManager.ListRegion.ITEMS.regionList[8],9.5f),
        SlotItem(10, SpriteManager.ListRegion.ITEMS.regionList[9],10.9f),
    )

}