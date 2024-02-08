package com.vbt.game.sptr.game.actors.slot

class Matrix3x4(
    private val winItemList: List<Item>? = null,
    val scheme: String = "",

    a1: Item,
    a2: Item,
    a3: Item,
    a4: Item,

    b1: Item,
    b2: Item,
    b3: Item,
    b4: Item,

    c1: Item,
    c2: Item,
    c3: Item,
    c4: Item,

    ) {

    private val slotA = listOf<Item>(a1, a2, a3, a4)
    private val slotB = listOf<Item>(b1, b2, b3, b4)
    private val slotC = listOf<Item>(c1, c2, c3, c4)

    private val slotList = listOf(slotA, slotB, slotC)

    private lateinit var shuffledSlotItemList: List<SlotItem>

    var winSlotItemSet: Set<SlotItem>? = null
        private set
    var intersectionList: List<Intersection>? = null
        private set



    private fun generateAndSetData() {
        if (this::shuffledSlotItemList.isInitialized.not()) throw Exception("Используй метод initialize")

        val tmpWinSlotItemSet   = mutableSetOf<SlotItem>()
        val tmpIntersectionList = mutableListOf<Intersection>()

        winItemList?.let { winList ->
            slotList.onEachIndexed { slotIndex, slot ->
                slot.onEachIndexed { itemIndex, item ->
                    if (winList.contains(item)) {
                        when (item) {
                            Item.W -> SlotItemContainer.wild
                            else   -> shuffledSlotItemList[item.index]
                        }.also { slotItem -> tmpWinSlotItemSet.add(slotItem) }
                        tmpIntersectionList.add(Intersection(slotIndex = slotIndex, rowIndex = itemIndex))
                    }
                }
            }
        }

        winSlotItemSet   = tmpWinSlotItemSet.ifEmpty { null }
        intersectionList = tmpIntersectionList.ifEmpty { null }

    }



    fun initialize(): Matrix3x4 {
        shuffledSlotItemList = SlotItemContainer.list.shuffled()
        generateAndSetData()
        return this
    }

    fun generateSlot(slotIndex: Int): List<SlotItem> = mutableListOf<SlotItem>().apply {
        if (this@Matrix3x4::shuffledSlotItemList.isInitialized.not()) throw Exception("Используй метод initialize")

        slotList[slotIndex].onEach { item ->
            when (item) {
                Item.W -> SlotItemContainer.wild
                else   -> shuffledSlotItemList[item.index]
            }.also { slotItem -> add(slotItem) }
        }
    }



    data class Intersection(
        val slotIndex: Int,
        val rowIndex : Int,
    )

    enum class Item(val index: Int) {
        W(SlotItemContainer.SLOT_ITEM_WILD_ID),

        a1(0),
        a2(1),
        a3(2),
        a4(3),
        a5(4),
        a6(5),
        a7(6),
        a8(7),
    }

}
