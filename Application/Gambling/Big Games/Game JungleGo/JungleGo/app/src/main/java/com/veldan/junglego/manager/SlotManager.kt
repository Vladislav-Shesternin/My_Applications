package com.veldan.junglego.manager

import com.veldan.junglego.actors.slot.*
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.SpriteManager.SlotItemSpriteList.SLOT_ITEM_LIST
import com.veldan.junglego.utils.Once
import com.veldan.junglego.utils.log
import kotlinx.coroutines.*
import kotlin.random.Random

class SlotManager(
    val slotGroup: SlotGroup,
    val slotList: List<Slot>,
) {

    companion object {
        const val DELAY_BETWEEN_SPIN = 1000L
        const val SLOT_ITEM_ALL_ID = 100
        const val SLOT_ITEM_BONUS_ID = 101

        const val MINI_GAME_SLOT_WITH_BONUS_COUNT = 2
        const val SUPER_GAME_SLOT_WITH_BONUS_COUNT = 3
    }

    private val slotItemAll = SlotItem(SLOT_ITEM_ALL_ID, 3f, SpriteManager.GameSprite.SLOT_ITEM_ALL.textureData.texture)
    private val slotItemBonus = SlotItem(SLOT_ITEM_BONUS_ID, 0f, SpriteManager.GameSprite.SLOT_ITEM_BONUS.textureData.texture)

    private val slotItemList = listOf<SlotItem>(
        slotItemAll,
        SlotItem(0, 1f, SLOT_ITEM_LIST.textureDataList[0].texture),
        SlotItem(1, 1.1f, SLOT_ITEM_LIST.textureDataList[1].texture),
        SlotItem(2, 1.2f, SLOT_ITEM_LIST.textureDataList[2].texture),
        SlotItem(3, 1.3f, SLOT_ITEM_LIST.textureDataList[3].texture),
        SlotItem(4, 1.4f, SLOT_ITEM_LIST.textureDataList[4].texture),
        SlotItem(5, 1.5f, SLOT_ITEM_LIST.textureDataList[5].texture),
        SlotItem(6, 1.6f, SLOT_ITEM_LIST.textureDataList[6].texture),
        SlotItem(7, 2f, SLOT_ITEM_LIST.textureDataList[7].texture),
    )

    private var winNumber = (1..5).random()
    private var miniGameNumber = (3..7).random()
    private var superGameNumber = (4..8).random()

    private var winSpinCounter = 0
    private var miniGameSpinCounter = 0
    private var superGameSpinCounter = 0

    private var bonus: Bonus? = null

    val rowWinIndexList = mutableListOf<Int>()



    suspend fun spin() = coroutineScope {
        winSpinCounter++
        miniGameSpinCounter++
        superGameSpinCounter++

        log(
            """
            
            winSpinCounter = $winSpinCounter WIN_NUM = $winNumber
            miniGameSpinCounter = $miniGameSpinCounter MINI_NUM = $miniGameNumber
            superGameSpinCounter = $superGameSpinCounter SUPER_NUM = $superGameNumber
            """
        )

        fillSlots()

        slotList.onEach { slot ->
            launch { slot.spin() }
            delay(DELAY_BETWEEN_SPIN)
        }
        delay(Slot.DELAY_SPIN - DELAY_BETWEEN_SPIN)

        SpinResult(
            slotItemList = checkWin().apply { if(isNotEmpty()) resetWin() },
            bonus = checkBonus()?.apply { resetBonus() }
        )
    }



    private fun fillSlots() {
        when {
            superGameSpinCounter == superGameNumber -> {
                fill(FillStrategy.SUPER_GAME)
                bonus = Bonus.SUPER_GAME
            }
            miniGameSpinCounter == miniGameNumber   -> {
                fill(FillStrategy.MINI_GAME)
                bonus = Bonus.MINI_GAME
            }
            winSpinCounter == winNumber             -> {
                fill(FillStrategy.WIN)
            }
            else                                    -> {
                fill(FillStrategy.RANDOM)
            }
        }
    }

    private fun fill(fillStrategy: FillStrategy) {
        when (fillStrategy) {
            FillStrategy.SUPER_GAME -> {
                log("FILL SUPER GAME")
                fillBonus(SUPER_GAME_SLOT_WITH_BONUS_COUNT)
            }
            FillStrategy.MINI_GAME  -> {
                log("FILL MINI GAME")
                fillBonus(MINI_GAME_SLOT_WITH_BONUS_COUNT)
            }
            FillStrategy.WIN        -> {
                log("FILL WIN")
                val slotItemWin = slotItemList.random()
                val rowIndex = (0 until Slot.VISIBLE_SLOT_ITEM_COUNT).random()
                val slotItemAllSlotIndex = (slotList.indices).random()

                log("FILL WIN | row = ${rowIndex.inc()}")
                slotList.onEachIndexed { index, slot ->
                    generateRandomSlotItemList().apply {
                        slot.publicSlotItemWinList = toMutableList().apply {
                            set(rowIndex, slotItemWin)
                            if (slotItemAllSlotIndex == index && Random.nextBoolean()) {
                                log("FILL WIN | slotItemAllSlotIndex = ${slotItemAllSlotIndex.inc()}")
                                set(rowIndex, slotItemAll)
                            }
                        }
                    }
                }
            }
            FillStrategy.RANDOM     -> {
                log("FILL RANDOM")
                val rowIndex = (0 until Slot.VISIBLE_SLOT_ITEM_COUNT).random()
                val slotItemBonusSlotIndex = (slotList.indices).random()

                slotList.onEachIndexed { index, slot ->
                    generateRandomSlotItemList().apply {
                        slot.publicSlotItemWinList = toMutableList().apply {
                            // { (1..5).random() == 1 } - 20%
                            if (slotItemBonusSlotIndex == index && (1..5).random() == 1) {
                                log("FILL RANDOM BONUS | slot ${slotItemBonusSlotIndex.inc()} | row = ${rowIndex.inc()}")
                                set(rowIndex, slotItemBonus)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun fillBonus(bonusCount: Int) {
        val slotWithBonusIndexList = List(Slot.VISIBLE_SLOT_ITEM_COUNT) { it }.shuffled().take(bonusCount)
        log("FILL BONUS GAME | randomSlotIndexList = ${slotWithBonusIndexList.sorted().map { it.inc() }}")

        slotList.onEachIndexed { index, slot -> generateRandomSlotItemList().also { randomSlotItemList ->
            slot.publicSlotItemWinList =
                if (slotWithBonusIndexList.contains(index)) {
                    randomSlotItemList.toMutableList().apply { set((0 until Slot.VISIBLE_SLOT_ITEM_COUNT).random(), slotItemBonus) }
                } else randomSlotItemList
        } }
    }

    private fun generateRandomSlotItemList() = List(Slot.VISIBLE_SLOT_ITEM_COUNT) { slotItemList.random() }


    /**
     * rowSlotItemIdSet - содержит ряд с каждого слота, был выбран Set потому-что содержит только уникальные элементы,
     * тоесть в него будут добавлены только уникальные SlotItem каждого элемента, если Set содержит:
     *    - 1 элемент -> элементы одинаковы;
     *    - 2 элемента и ОДИН из них SLOT_ITEM_ALL (универсальный) -> элементы одинаковы;
     *    - 2 элемента и НЕ ОДИН из них SLOT_ITEM_ALL (универсальный) -> элементы одинаковы;
     *    - более 2 элементов -> элементы разные;
     * */
    private fun checkWin(): List<SlotItem> {
        val slotItemWinList = mutableListOf<SlotItem>()

        var match: Boolean
        var rowSlotItemSet: MutableSet<SlotItem>
        var slotItemWin: SlotItem

        rowWinIndexList.clear()

        repeat(Slot.VISIBLE_SLOT_ITEM_COUNT) { rowIndex ->
            rowSlotItemSet = mutableSetOf()

            slotList.onEach { slot -> rowSlotItemSet.add(slot.publicSlotItemWinList[rowIndex]) }

            //  - более 2 элементов -> элементы разные;
            match = if (rowSlotItemSet.size in 1..2) {
                when {
                    // - 1 элемент -> элементы одинаковы;
                    rowSlotItemSet.size == 1             -> true
                    // - 2 элемента и ОДИН из них SLOT_ITEM_ALL (универсальный) -> элементы одинаковы;
                    rowSlotItemSet.contains(slotItemAll) -> true
                    // - 2 элемента и НЕ ОДИН из них SLOT_ITEM_ALL (универсальный) -> элементы одинаковы;
                    else                                 -> false
                }
            } else false

            if (match) {
                slotItemWin = if (rowSlotItemSet.size == 2) slotItemAll else rowSlotItemSet.first()
                slotItemWinList.add(slotItemWin)
                rowWinIndexList.add(rowIndex)
                log("WIN_ROW = ${rowIndex.inc()}")
            }
        }

        return slotItemWinList
    }

    private suspend fun checkBonus() = CompletableDeferred<Bonus?>().also { continuation ->
        when (bonus) {
            Bonus.MINI_GAME  -> { with(slotGroup){
                addMiniGameGroup()
                miniGameGroup.resultBlock = { bonusPrice -> CoroutineScope(Dispatchers.Default).launch {
                    removeMiniGameGroup()
                    continuation.complete(Bonus.MINI_GAME.apply { price = bonusPrice })
                    cancel()
                } }
            } }
            Bonus.SUPER_GAME -> { with(slotGroup) {
                addSuperGameGroup()
                superGameGroup.resultBlock = { bonusPrice -> CoroutineScope(Dispatchers.Default).launch {
                    removeSuperGameGroup()
                    continuation.complete(Bonus.SUPER_GAME.apply { price = bonusPrice })
                    cancel()
                } }
            } }
            else             -> {
                continuation.complete(null)
            }
        }
    }.await()



    private fun resetWin() {
        winSpinCounter = 0
        winNumber = (1..5).random()
    }

    private fun resetBonus() {
        when (bonus) {
            Bonus.MINI_GAME -> {
                miniGameSpinCounter = 0
                miniGameNumber = (3..7).random()
                resetWin()
            }
            Bonus.SUPER_GAME -> {
                superGameSpinCounter = 0
                superGameNumber = (4..8).random()
                resetWin()
            }
            else -> { }
        }
        bonus = null
    }



    enum class FillStrategy {
        RANDOM, WIN, MINI_GAME, SUPER_GAME
    }

}