package com.veldan.sportslots.utils.slot

import com.veldan.sportslots.actors.slot.Slot
import com.veldan.sportslots.assets.SpriteManager
import com.veldan.sportslots.utils.log
import com.veldan.sportslots.utils.slot.SlotManager.Strategy.*
import kotlin.random.Random

const val SLOT_ITEM_ALL_ID = 100
const val SLOT_ITEM_BONUS_ID = 1000

class SlotManager(
     vararg val slots: Slot
) {

    private val itemList = listOf<SlotItem>(
       SlotItem(0, 1500, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[0].texture),
       SlotItem(1, 2000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[1].texture),
       SlotItem(2, 5000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[2].texture),
       SlotItem(3, 7000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[3].texture),
       SlotItem(4, 9500, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[4].texture),
       SlotItem(5, 12000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[5].texture),
       SlotItem(6, 15000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[6].texture),
       SlotItem(7, 17000, SpriteManager.GameSpriteList.ITEM_LIST.textureDataList[7].texture),
    )

    private val itemAll = SlotItem(SLOT_ITEM_ALL_ID, 0, SpriteManager.GameSprite.ALL.textureData.texture)
    private val itemBonus = SlotItem(SLOT_ITEM_BONUS_ID, 0, SpriteManager.GameSprite.BONUS.textureData.texture)

    private var listWinItem = mutableListOf<SlotItem>()
    private var listBonusRandomSlotIndex = mutableListOf<Int>()



    fun fill(strategy: Strategy) {
        when (strategy) {
            WIN    -> {
                val row = Random.nextInt(0, 2)
                val item = itemList.random()
                slots.onEach { slot ->
                    listWinItem = MutableList(3) { itemList.random() }.apply { this[row] = item }
                    slot.setWin(listWinItem)
                }
            }
            RANDOM -> {
                slots.onEach { slot ->
                    listWinItem = MutableList(3) { itemList.random() }
                    slot.setWin(listWinItem)
                }
            }
            MINI_GAME -> {
                val row = Random.nextInt(0, 2)
                listBonusRandomSlotIndex = mutableListOf<Int>().apply {
                    repeat(3) {
                        val indexes = MutableList(slots.size) { it }.minus(this)
                        add(indexes.random())
                    }
                }
                slots.onEachIndexed { index, slot ->
                    listWinItem = MutableList(3) { itemList.random() }
                    listBonusRandomSlotIndex.onEach { if (it == index) listWinItem[row] = itemBonus }
                    slot.setWin(listWinItem)
                }
            }
            SUPER_GAME -> {
                val row = Random.nextInt(0, 2)
                listBonusRandomSlotIndex = mutableListOf<Int>().apply {
                    repeat(2) {
                        val indexes = MutableList(slots.size) { it }.minus(this)
                        add(indexes.random())
                    }
                }
                slots.onEachIndexed { index, slot ->
                    listWinItem = MutableList(3) { itemList.random() }
                    listBonusRandomSlotIndex.onEach { if (it == index) listWinItem[row] = itemBonus }
                    slot.setWin(listWinItem)
                }
            }

        }
        if (Random.nextBoolean() && strategy != MINI_GAME && strategy != SUPER_GAME) {
            val randomItemIndex = Random.nextInt(0, 2)
            listWinItem[randomItemIndex] = itemAll.apply { price = listWinItem[randomItemIndex].price }
            slots[Random.nextInt(0, slots.lastIndex)].setWin(listWinItem)
        }
    }



    fun getSecondSlotIndexWithBonus(): Int = if (listBonusRandomSlotIndex.isNotEmpty())
        listBonusRandomSlotIndex.sorted()[1]
    else throw Exception("listBonusRandomSlotIndex isEmpty = true")




    enum class Strategy{
        RANDOM, WIN, MINI_GAME, SUPER_GAME
    }

}