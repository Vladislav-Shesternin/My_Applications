package mst.mysteryof.antientegyptua.game.actors.slots.slot3x3

import mst.mysteryof.antientegyptua.game.actors.slots.SlotItem

class Matrix3x3Handler(
    private val matrix3x3        : Matrix3x3,
    private val slotItemContainer: SlotItemContainer
) {

    private val resultMatrix3x3 = matrix3x3.resultMatrix3x3

    private val slotA = listOf<Int>(matrix3x3.a1, matrix3x3.a2, matrix3x3.a3)
    private val slotB = listOf<Int>(matrix3x3.b1, matrix3x3.b2, matrix3x3.b3)
    private val slotC = listOf<Int>(matrix3x3.c1, matrix3x3.c2, matrix3x3.c3)

    private val glowA = listOf<Boolean>(resultMatrix3x3?.a1 ?: false, resultMatrix3x3?.a2 ?: false, resultMatrix3x3?.a3 ?: false)
    private val glowB = listOf<Boolean>(resultMatrix3x3?.b1 ?: false, resultMatrix3x3?.b2 ?: false, resultMatrix3x3?.b3 ?: false)
    private val glowC = listOf<Boolean>(resultMatrix3x3?.c1 ?: false, resultMatrix3x3?.c2 ?: false, resultMatrix3x3?.c3 ?: false)

    private val slots = listOf(slotA, slotB, slotC,)
    private val glows = listOf(glowA, glowB, glowC,)

    private val shuffledSlotItemList = slotItemContainer.list.shuffled()

    fun generateSlot(slotIndex: Int): List<SlotItem> {
        val slotItemList = mutableListOf<SlotItem>()

        slots[slotIndex].onEach { slotItemIndex ->
            val slotItem = when (slotItemIndex) {
//                13   -> slotItemContainer.wild
                else -> shuffledSlotItemList[slotItemIndex]
            }
            slotItemList.add(slotItem)
        }

        return slotItemList
    }

    fun generateGlow(glowIndex: Int): List<Int> {
        val indexList = mutableListOf<Int>()
        glows[glowIndex].onEachIndexed { index, isWin -> if (isWin) indexList.add(index) }
        return indexList
    }

}