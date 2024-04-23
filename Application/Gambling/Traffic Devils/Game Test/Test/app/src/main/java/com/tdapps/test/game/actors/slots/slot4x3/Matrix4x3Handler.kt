package com.tdapps.test.game.actors.slots.slot4x3

import com.tdapps.test.game.actors.slots.SlotItem

class Matrix4x3Handler(
    private val matrix4x3        : Matrix4x3,
    private val slotItemContainer: SlotItemContainer
) {

    private val resultMatrix4x3 = matrix4x3.resultMatrix4x3

    private val slotA = listOf<Int>(matrix4x3.a1, matrix4x3.a2, matrix4x3.a3)
    private val slotB = listOf<Int>(matrix4x3.b1, matrix4x3.b2, matrix4x3.b3)
    private val slotC = listOf<Int>(matrix4x3.c1, matrix4x3.c2, matrix4x3.c3)
    private val slotD = listOf<Int>(matrix4x3.d1, matrix4x3.d2, matrix4x3.d3)

    private val glowA = listOf<Boolean>(resultMatrix4x3?.a1 ?: false, resultMatrix4x3?.a2 ?: false, resultMatrix4x3?.a3 ?: false)
    private val glowB = listOf<Boolean>(resultMatrix4x3?.b1 ?: false, resultMatrix4x3?.b2 ?: false, resultMatrix4x3?.b3 ?: false)
    private val glowC = listOf<Boolean>(resultMatrix4x3?.c1 ?: false, resultMatrix4x3?.c2 ?: false, resultMatrix4x3?.c3 ?: false)
    private val glowD = listOf<Boolean>(resultMatrix4x3?.d1 ?: false, resultMatrix4x3?.d2 ?: false, resultMatrix4x3?.d3 ?: false)

    private val slots = listOf(slotA, slotB, slotC, slotD)
    private val glows = listOf(glowA, glowB, glowC, glowD)

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