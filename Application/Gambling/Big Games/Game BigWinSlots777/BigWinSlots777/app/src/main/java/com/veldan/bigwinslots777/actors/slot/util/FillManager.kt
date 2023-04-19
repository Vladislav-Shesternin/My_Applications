package com.veldan.bigwinslots777.actors.slot.util

import com.veldan.bigwinslots777.actors.miniGameGroup.MiniGameGroup
import com.veldan.bigwinslots777.actors.slot.slot.Slot
import com.veldan.bigwinslots777.actors.slot.slot.SlotController
import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.Combination
import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.CombinationMatrixEnum
import com.veldan.bigwinslots777.screens.game.GameScreenController
import com.veldan.bigwinslots777.utils.log
import com.veldan.bigwinslots777.utils.probability

class FillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("FILL_MIX")

        val combinationMatrixEnum: CombinationMatrixEnum = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            Combination.MixWithWild.values().random()
        } else Combination.Mix.values().random()

        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = combinationMatrix.generateSlot(index) }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
            *Combination.WinMonochrome.values(),
            *Combination.WinMonochromeWithWild.values(),
            *Combination.WinColorful.values(),
            *Combination.WinColorfulWithWild.values(),
        ).random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }
    }

    private fun fillMini() {
        log("FILL_MINI")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(2)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(3)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun fillMiniWildWin() {
        log("FILL_MINI_WILD_WIN")

        val combinationMatrixEnum: CombinationMatrixEnum = when (MiniGameGroup.slotIndex) {
            0    -> Combination.MiniWinWildSlots.values()[0].rowList[MiniGameGroup.rowIndex].combinationList.random()
            1    -> Combination.MiniWinWildSlots.values()[1].rowList[MiniGameGroup.rowIndex].combinationList.random()
            2    -> Combination.MiniWinWildSlots.values()[2].rowList[MiniGameGroup.rowIndex].combinationList.random()
            3    -> Combination.MiniWinWildSlots.values()[3].rowList[MiniGameGroup.rowIndex].combinationList.random()
            else -> Combination.MiniWinWildSlots.values()[0].rowList[MiniGameGroup.rowIndex].combinationList.random()
        }

        log("""
            SLOT: ${MiniGameGroup.slotIndex};
            ROW: ${MiniGameGroup.rowIndex};
        """.trimIndent())


        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }
    }

    private fun fillMiniWildFail() {
        log("FILL_MINI_WILD_FAIL")

        val combinationMatrixEnum: CombinationMatrixEnum = when (MiniGameGroup.slotIndex) {
            0    -> Combination.MiniFailWildSlots.values()[0].rowList[MiniGameGroup.rowIndex]
            1    -> Combination.MiniFailWildSlots.values()[1].rowList[MiniGameGroup.rowIndex]
            2    -> Combination.MiniFailWildSlots.values()[2].rowList[MiniGameGroup.rowIndex]
            3    -> Combination.MiniFailWildSlots.values()[3].rowList[MiniGameGroup.rowIndex]
            else -> Combination.MiniFailWildSlots.values()[0].rowList[MiniGameGroup.rowIndex]
        }

        log("""
            SLOT: ${MiniGameGroup.slotIndex};
            ROW: ${MiniGameGroup.rowIndex};
            scheme = ${combinationMatrixEnum.matrix.scheme}
        """.trimIndent())

        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }
    }

    private fun fillSuperWildWin() {
        log("FILL_SUPER_WILD_WIN")

        val combinationMatrixEnum = when (GameScreenController.numberWild) {
            1    -> Combination.SuperWinWild1.values()
            2    -> Combination.SuperWinWild2.values()
            3    -> Combination.SuperWinWild3.values()
            else -> Combination.SuperWinWild1.values()
        }.random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }
    }

    private fun fillSuperWildFail() {
        log("FILL_SUPER_WILD_FAIL")

        val combinationMatrixEnum = when (GameScreenController.numberWild) {
            1    -> Combination.SuperFailWild1.values()
            2    -> Combination.SuperFailWild2.values()
            3    -> Combination.SuperFailWild3.values()
            else -> Combination.SuperFailWild1.values()
        }.random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("scheme = ${combinationMatrix.scheme}")
    }

    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
        set(randomRow, slotItem)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            is FillStrategy.MIX             -> fillMix()
            is FillStrategy.WIN             -> fillWin()
            is FillStrategy.MINI            -> fillMini()
            is FillStrategy.SUPER           -> fillSuper()
            is FillStrategy.MINI_WILD_WIN   -> fillMiniWildWin()
            is FillStrategy.MINI_WILD_FAIL  -> fillMiniWildFail()
            is FillStrategy.SUPER_WILD_WIN  -> fillSuperWildWin()
            is FillStrategy.SUPER_WILD_FAIL -> fillSuperWildFail()
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination.Mix                   -> "Mix $name"
            is Combination.MixWithWild           -> "Mix WILD $name"
            is Combination.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination.WinColorful           -> "Win COLORFUL $name"
            is Combination.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
            is Combination.SuperWinWild1         -> "Super Win WILD 1 $name"
            is Combination.SuperWinWild2         -> "Super Win WILD 2 $name"
            is Combination.SuperWinWild3         -> "Super Win WILD 3 $name"
            is Combination.SuperFailWild1        -> "Super Fail WILD 1 $name"
            is Combination.SuperFailWild2        -> "Super Fail WILD 2 $name"
            is Combination.SuperFailWild3        -> "Super Fail WILD 3 $name"
            else                                 -> "Noname"
        }
        log(combinationEnumName)
    }

}