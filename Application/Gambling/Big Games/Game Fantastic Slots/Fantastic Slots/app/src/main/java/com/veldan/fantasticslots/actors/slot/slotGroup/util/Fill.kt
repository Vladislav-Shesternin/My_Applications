package com.veldan.fantasticslots.actors.slot.slotGroup.util

import com.veldan.fantasticslots.actors.slot.Combination
import com.veldan.fantasticslots.actors.slot.FillStrategy
import com.veldan.fantasticslots.actors.slot.SlotItem
import com.veldan.fantasticslots.actors.slot.slot.Slot
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.assets.SpriteManager.SlotItemSpriteList.*
import com.veldan.fantasticslots.utils.log
import com.veldan.fantasticslots.utils.probability

class Fill(val slotList: List<Slot>) {

    companion object {
        private const val SLOT_ITEM_WILD_ID    = 100
        private const val SLOT_ITEM_SCATTER_ID = 101

        val slotItemWild    = SlotItem(SLOT_ITEM_WILD_ID   , 3f, SpriteManager.GameSprite.WILD.data.texture   )
        val slotItemScatter = SlotItem(SLOT_ITEM_SCATTER_ID, 0f, SpriteManager.GameSprite.SCATTER.data.texture)

        val slotItemList = listOf<SlotItem>(
            SlotItem(0, 1f  , SLOT_ITEM_LIST.dataList[0].texture),
            SlotItem(1, 1.1f, SLOT_ITEM_LIST.dataList[1].texture),
            SlotItem(2, 1.2f, SLOT_ITEM_LIST.dataList[2].texture),
            SlotItem(3, 1.3f, SLOT_ITEM_LIST.dataList[3].texture),
            SlotItem(4, 1.4f, SLOT_ITEM_LIST.dataList[4].texture),
            SlotItem(5, 1.5f, SLOT_ITEM_LIST.dataList[5].texture),
            SlotItem(6, 1.6f, SLOT_ITEM_LIST.dataList[6].texture),
            SlotItem(7, 1.7f, SLOT_ITEM_LIST.dataList[7].texture),
            SlotItem(8, 1.8f, SLOT_ITEM_LIST.dataList[8].texture),
            SlotItem(9, 2f  , SLOT_ITEM_LIST.dataList[9].texture),
        )

    }



    private fun fillRandom(useSlotItemScatter: Boolean = true) {
        log("FILL_RANDOM")

        slotList.onEach { slot ->
            slot.slotItemWinList = mutableListOf<SlotItem>().apply { fillRandom() }
        }

        probability(20) {
            log("FILL_RANDOM set WILD")
            with(slotList.random()) { slotItemWinList = slotItemWinList.toMutableList().apply { setInRandomRow(slotItemWild) } }
        }

        if (useSlotItemScatter) probability(30) {
            log("FILL_RANDOM set SCATTER")
            with(slotList.random()) { slotItemWinList = slotItemWinList.toMutableList().apply { setInRandomRow(slotItemScatter) } }
        }
    }

    private fun fillWin() {
        log("FILL_WIN")
        fillRandom(false)

        val slotItemWin = slotItemList.random()
        val slotCount   = Combination.values().random().slotCount

        log("FILL_WIN slot_count = $slotCount")

        slotList.onEachIndexed { index, slot ->
            if (index >= slotCount) return@onEachIndexed

            val randomRowCount = (1 until Slot.VISIBLE_SLOT_ITEM_COUNT).random()
            val randomRowList = List(Slot.VISIBLE_SLOT_ITEM_COUNT) { it }.shuffled().take(randomRowCount)

            randomRowList.onEach { row -> slot.apply { slotItemWinList = slotItemWinList.toMutableList().apply { set(row, slotItemWin) } } }
        }

        probability(20) {
            log("FILL_WIN set WILD")
            with(slotList.random()) { slotItemWinList = slotItemWinList.toMutableList().apply { setInRandomRow(slotItemWild) } }
        }

    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillRandom(false)

        val randomSlotList = slotList.shuffled().take(3)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply { setInRandomRow(slotItemScatter) } } }
    }



    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) {
        set((0 until Slot.VISIBLE_SLOT_ITEM_COUNT).random(), slotItem)
    }

    private fun MutableList<SlotItem>.fillRandom() {
        repeat(Slot.VISIBLE_SLOT_ITEM_COUNT) { add(slotItemList.random()) }
    }



    fun fill(fillStrategy: FillStrategy) {
        when (fillStrategy) {
            is FillStrategy.RANDOM -> fillRandom()
            is FillStrategy.WIN    -> fillWin()
            is FillStrategy.SUPER  -> fillSuper()
        }
    }

}