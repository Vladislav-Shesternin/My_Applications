package com.book.of.dead.deluxe.game.actors.slot

import com.book.of.dead.deluxe.game.manager.SpriteManager

object SlotItemContainer {

    const val SLOT_ITEM_WILD_ID = 10
    const val SLOT_ITEM_BONUS_WILD_ID = 9

    val wild = SlotItem(SLOT_ITEM_WILD_ID, SpriteManager.GameRegion.WILD.region, 30f)

    val list = listOf<SlotItem>(
        SlotItem(1, SpriteManager.ListRegion.ITEMS.regionList[0],2f),
        SlotItem(2, SpriteManager.ListRegion.ITEMS.regionList[1],4f),
        SlotItem(3, SpriteManager.ListRegion.ITEMS.regionList[2],8f),
        SlotItem(4, SpriteManager.ListRegion.ITEMS.regionList[3],10f),
        SlotItem(5, SpriteManager.ListRegion.ITEMS.regionList[4],12f),
        SlotItem(6, SpriteManager.ListRegion.ITEMS.regionList[5],14f),
        SlotItem(7, SpriteManager.ListRegion.ITEMS.regionList[6],16f),
        SlotItem(8, SpriteManager.ListRegion.ITEMS.regionList[7],18f),
        SlotItem(SLOT_ITEM_BONUS_WILD_ID, SpriteManager.GameRegion.BONUS_WILD.region,20f),
    )

}