package com.tmesfo.frtunes.game.actors.slot.util

import com.tmesfo.frtunes.game.actors.slot.slot.Slot
import com.tmesfo.frtunes.game.actors.slot.util.combination._3x3.Combination3x3
import com.tmesfo.frtunes.game.actors.slot.util.combination._3x3.CombinationMatrixEnum3x3
import com.tmesfo.frtunes.game.actors.slot.util.combination._3x4.Combination3x4
import com.tmesfo.frtunes.game.actors.slot.util.combination._3x4.CombinationMatrixEnum3x4
import com.tmesfo.frtunes.util.log
import com.tmesfo.frtunes.util.probability

class FillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("FILL_MIX")

        val combinationMatrixEnum: CombinationMatrixEnum3x3 = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            Combination3x3.MixWithWild.values().random()
        } else Combination3x3.Mix.values().random()

        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = combinationMatrix.generateSlot(index) }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum3x3>(
            *Combination3x3.WinMonochrome.values(),
            *Combination3x3.WinMonochromeWithWild.values(),
            *Combination3x3.WinColorful.values(),
            *Combination3x3.WinColorfulWithWild.values(),
        ).random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = combinationMatrix.generateSlot(index) }

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


    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            is FillStrategy.MIX -> fillMix()
            is FillStrategy.WIN -> fillWin()
        }
    }



    private fun CombinationMatrixEnum3x3.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination3x3.Mix                   -> "Mix $name"
            is Combination3x3.MixWithWild           -> "Mix WILD $name"
            is Combination3x3.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination3x3.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination3x3.WinColorful           -> "Win COLORFUL $name"
            is Combination3x3.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
            else                                    -> "Noname"
        }
        log(combinationEnumName)
    }

}