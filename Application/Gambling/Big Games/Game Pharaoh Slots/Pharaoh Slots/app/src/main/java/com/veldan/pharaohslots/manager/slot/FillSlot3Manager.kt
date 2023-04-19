package com.veldan.pharaohslots.manager.slot

import com.veldan.pharaohslots.actors.slot.*
import com.veldan.pharaohslots.assets.SpriteManager
import com.veldan.pharaohslots.assets.SpriteManager.SlotItemSpriteList.*
import com.veldan.pharaohslots.utils.log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.random.Random

class FillSlot3Manager(
    val slotList: List<Slot3Animated>,
) {

    companion object {
        private const val SLOT_ITEM_ALL_ID   = 100
        private const val SLOT_ITEM_BONUS_ID = 101

        val slotItemAll   = SlotItem(SLOT_ITEM_ALL_ID  , 3f, SpriteManager.GameSprite.SLOT_ITEM_ALL.data.texture  )
        val slotItemBonus = SlotItem(SLOT_ITEM_BONUS_ID, 0f, SpriteManager.GameSprite.SLOT_ITEM_BONUS.data.texture)

        // 9 - элементов необходимо для текущего алгоритма заполнения
        val slotItemList = listOf<SlotItem>(
            SlotItem(0, 1f  , SLOT_ITEM_LIST.dataList[0].texture),
            SlotItem(1, 1.1f, SLOT_ITEM_LIST.dataList[1].texture),
            SlotItem(2, 1.2f, SLOT_ITEM_LIST.dataList[2].texture),
            SlotItem(3, 1.3f, SLOT_ITEM_LIST.dataList[3].texture),
            SlotItem(4, 1.4f, SLOT_ITEM_LIST.dataList[4].texture),
            SlotItem(5, 1.5f, SLOT_ITEM_LIST.dataList[5].texture),
            SlotItem(6, 1.6f, SLOT_ITEM_LIST.dataList[6].texture),
            SlotItem(7, 1.7f, SLOT_ITEM_LIST.dataList[7].texture),
            SlotItem(8, 2f  , SLOT_ITEM_LIST.dataList[8].texture),
        )

    }

    var fillResultList: List<FillResult>? = null
        private set



    private fun fillMix(isUseSlotItemAll: Boolean = true, isUseSlotItemBonus: Boolean = true) {
        log("FILL_MIX")
        // Список состоящий со списков по 3 элемента
        val by3SlotItemListList = slotItemList.shuffled().chunked(3)

        slotList.onEachIndexed { index, slot ->
            when (index) {
                in 0..2 -> slot.slotItemList = by3SlotItemListList[index]
                3       -> slot.slotItemList = by3SlotItemListList[0]
                4       -> slot.slotItemList = by3SlotItemListList[1]
            }
        }

        // Установить в рандомный слот и ряд универсальный элемент
        if (isUseSlotItemAll) {
            // 33%
            val randomNum = (1..3).random()
            if (randomNum == 1) slotList.random().apply { slotItemList = slotItemList.setSlotItemAll() }
        }
        // Установить в рандомный слот и ряд бонусный элемент
        if (isUseSlotItemBonus) {
            // 10%
            val randomNum = (1..10).random()
            if (randomNum == 1) slotList.random().apply { slotItemList = slotItemList.setSlotItemBonus() }
        }
    }

    private fun fillWin(isUseSlotItemAll: Boolean = false) {
        log("FILL_WIN")
        fillMix(!isUseSlotItemAll)

        val tmpFillResultList = mutableListOf<FillResult>()

        // Использовать комбинацию или групу комбинаций?
        val isUseCombination = Random.nextBoolean()

        // Вероятность использования 1 комбинации или групы комбинаций 50% на 50%
        var combinationList: List<Combination> = if (isUseCombination) listOf(Combination.values().random()) else CombinationGroup.values().random().combinationList

        // Если размер списока комбинаций больше 1 то это комбинационная група
        // Если это она то нужно выбрать рандомное количество комбинаций от 2 до всех комбинаций в групе
        // Это разнообразит комбинации побед
        with(combinationList) { if (size > 1) combinationList = shuffled().take((2..size).random()) }

        log("FILL WIN COMBINATION: | count = ${combinationList.size} | ${combinationList.joinToString()}")

        // Заполняем слоты по комбинациям
        combinationList.onEach { combination ->
            val winSlotItem = slotItemList.random()

            tmpFillResultList.add(FillResult(combination, winSlotItem))

            // Если 1 слот (100% множество рядов) | VERTICAL
            if (combination.slotIndexList.size == 1) {
                val slotIndex   = combination.slotIndexList.first()
                val winSlotItemList = List(3) { winSlotItem }

                // Устанавливаем в определённый в комбинации слот список элементов
                slotList[slotIndex].slotItemList = winSlotItemList
            }
            // Если множество слотов (50% 1 ряд | 50% множество рядов)
            else {
                // Если 1 ряд | HORIZONTAL
                if (combination.rowIndexList.size == 1) {
                    val rowIndex = combination.rowIndexList.first()

                    combination.slotIndexList.onEach { slotIndex ->
                        // Устанавливаем в слот определённый в комбинации элементы с или без универсального элемента
                        slotList[slotIndex].apply { slotItemList = slotItemList.toMutableList().apply { set(rowIndex, winSlotItem) } }
                    }
                }
                // Если множество рядов | DIAGONAL_UP, DIAGONAL_DOWN
                else {
                    combination.slotIndexList.onEachIndexed { index, slotIndex ->
                        slotList[slotIndex].apply { slotItemList = slotItemList.toMutableList().apply { set(combination.rowIndexList[index], winSlotItem) } }
                    }
                }
            }
        }

        // Установить в рандомный слот и ряд универсальный элемент
        if (isUseSlotItemAll) slotList.random().apply { slotItemList = slotItemList.setSlotItemAll() }

        fillResultList = if (tmpFillResultList.isNotEmpty()) tmpFillResultList else null
    }

    private fun fillMini() {
        log("FILL_MINI")
        fillMix(isUseSlotItemAll = true, isUseSlotItemBonus = false)
        // Устанавливаем в 2 рандомных слоты и ряды по 1 бонусному элементу
        slotList.shuffled().take(2).onEach { slot -> slot.apply { slotItemList = slotItemList.setSlotItemBonus() } }
    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillMix(isUseSlotItemAll = true, isUseSlotItemBonus = false)
        // Устанавливаем в 3 рандомных слоты и ряды по 1 бонусному элементу
        slotList.shuffled().take(3).onEach { slot -> slot.apply { slotItemList = slotItemList.setSlotItemBonus() } }
    }



    fun fill(fillStrategy: FillStrategy) {
        fillResultList = null

        when (fillStrategy) {
            is FillStrategy.MIX   -> fillMix(fillStrategy.isUseSlotItemAll, fillStrategy.isUseSlotItemBonus)
            is FillStrategy.WIN   -> fillWin(fillStrategy.isUseSlotItemAll)
            is FillStrategy.MINI  -> fillMini()
            is FillStrategy.SUPER -> fillSuper()
        }
    }



    private fun List<SlotItem>.setSlotItemAll() = toMutableList().apply { set((0..2).random(), slotItemAll) }

    private fun List<SlotItem>.setSlotItemBonus() = toMutableList().apply { set((0..2).random(), slotItemBonus) }

}