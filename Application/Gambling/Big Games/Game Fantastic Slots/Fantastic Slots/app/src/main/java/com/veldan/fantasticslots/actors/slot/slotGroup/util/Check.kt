package com.veldan.fantasticslots.actors.slot.slotGroup.util

import com.veldan.fantasticslots.actors.slot.CheckResult
import com.veldan.fantasticslots.actors.slot.CheckSlot
import com.veldan.fantasticslots.actors.slot.SlotItem
import com.veldan.fantasticslots.actors.slot.slot.Slot
import com.veldan.fantasticslots.actors.slot.slotGroup.util.Fill.Companion.slotItemWild

class Check(val slotList: List<Slot>) {

    private lateinit var winSlotItemSet  : MutableSet<SlotItem>
    private lateinit var checkSlotList   : MutableList<CheckSlot>


    private fun fillWinSlotItemSet() {
        slotList.first().slotItemWinList.onEach { slotItem ->
            if (slotList[1].slotItemWinList.map { it.id }.contains(slotItem.id) &&
                slotList[2].slotItemWinList.map { it.id }.contains(slotItem.id)
            ){
                winSlotItemSet.add(slotItem)
                if (slotList.map { it.slotItemWinList }.flatten().contains(slotItemWild)) winSlotItemSet.add(slotItemWild)
            }
        }

        if (slotList[0].slotItemWinList.contains(slotItemWild)) {
            slotList[1].slotItemWinList.onEach { slotItem ->
                if (slotList[2].slotItemWinList.map { it.id }.contains(slotItem.id)) {
                    winSlotItemSet.add(slotItem)
                    winSlotItemSet.add(slotItemWild)
                }
            }
        }

        if (slotList[1].slotItemWinList.contains(slotItemWild)) {
            slotList[0].slotItemWinList.onEach { slotItem ->
                if (slotList[2].slotItemWinList.map { it.id }.contains(slotItem.id)) {
                    winSlotItemSet.add(slotItem)
                    winSlotItemSet.add(slotItemWild)
                }
            }
        }
        if (slotList[2].slotItemWinList.contains(slotItemWild)) {
            slotList[0].slotItemWinList.onEach { slotItem ->
                if (slotList[1].slotItemWinList.map { it.id }.contains(slotItem.id)){
                    winSlotItemSet.add(slotItem)
                    winSlotItemSet.add(slotItemWild)
                }
            }
        }
    }

    private fun fillCheckSlotList() {
        val winSlotItemIdSet = winSlotItemSet.map { it.id }
        var winRowList: MutableList<Int>

        slotList.onEachIndexed { slotIndex, slot ->
            winRowList = mutableListOf()

            slot.slotItemWinList.onEachIndexed { index, slotItem ->
                if (winSlotItemIdSet.contains(slotItem.id)) winRowList.add(index)
            }

            if (winRowList.isNotEmpty()) checkSlotList.add(CheckSlot(slotIndex, winRowList))
            else return
        }
    }



    fun checkWin(): CheckResult {
        winSlotItemSet = mutableSetOf()
        checkSlotList  = mutableListOf()

        val checkResult = CheckResult(checkSlotList, winSlotItemSet)

        fillWinSlotItemSet()

        if (winSlotItemSet.isEmpty()) return checkResult

        fillCheckSlotList()

        return checkResult
    }

}