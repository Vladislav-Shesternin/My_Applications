package com.veldan.fantasticslots.actors.roulette

import com.veldan.fantasticslots.actors.slot.slotGroup.util.Fill
import com.veldan.fantasticslots.actors.slot.SlotItem
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.log

object SuperRoulette {

    private const val SEGMENT = 36f

    private val itemList = listOf<RouletteItem>(
        RouletteItem(slotItem = Fill.slotItemList[0], segment = (SEGMENT * 0f) + 0.01f to (SEGMENT * 1f)),
        RouletteItem(slotItem = Fill.slotItemList[5], segment = (SEGMENT * 1f) + 0.01f to (SEGMENT * 2f)),
        RouletteItem(slotItem = Fill.slotItemList[1], segment = (SEGMENT * 2f) + 0.01f to (SEGMENT * 3f)),
        RouletteItem(slotItem = Fill.slotItemList[6], segment = (SEGMENT * 3f) + 0.01f to (SEGMENT * 4f)),
        RouletteItem(slotItem = Fill.slotItemList[2], segment = (SEGMENT * 4f) + 0.01f to (SEGMENT * 5f)),
        RouletteItem(slotItem = Fill.slotItemList[7], segment = (SEGMENT * 5f) + 0.01f to (SEGMENT * 6f)),
        RouletteItem(slotItem = Fill.slotItemList[3], segment = (SEGMENT * 6f) + 0.01f to (SEGMENT * 7f)),
        RouletteItem(slotItem = Fill.slotItemList[9], segment = (SEGMENT * 7f) + 0.01f to (SEGMENT * 8f)),
        RouletteItem(slotItem = Fill.slotItemList[4], segment = (SEGMENT * 8f) + 0.01f to (SEGMENT * 9f)),
        RouletteItem(slotItem = Fill.slotItemList[8], segment = (SEGMENT * 9f) + 0.01f to (SEGMENT * 10f)),
    )

    val roulette: Roulette get() {
        return Roulette(
            texture = SpriteManager.GameSprite.ROULETTE.data.texture,
            rouletteItemList = itemList
        )
    }



        class RouletteItem(
            val slotItem: SlotItem,
            override val segment: Pair<Float, Float>,
        ): Roulette.RouletteItem

}





