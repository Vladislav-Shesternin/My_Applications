package com.gen.bettermeditatio.game.actors.slot

import com.gen.bettermeditatio.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 10f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],20f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],30f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],40f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],50f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],60f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],70f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],80f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],90f),
    )

}