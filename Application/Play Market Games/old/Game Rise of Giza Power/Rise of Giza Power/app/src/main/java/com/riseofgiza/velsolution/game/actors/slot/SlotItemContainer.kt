package com.riseofgiza.velsolution.game.actors.slot

import com.riseofgiza.velsolution.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 7

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 20f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],2f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],4f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],8f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],10f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],12f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],14f),
    )

}