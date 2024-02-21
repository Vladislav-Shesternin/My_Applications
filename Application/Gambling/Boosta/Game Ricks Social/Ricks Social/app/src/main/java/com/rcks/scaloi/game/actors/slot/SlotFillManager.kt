package com.rcks.scaloi.game.actors.slot

import com.rcks.scaloi.util.log
import com.rcks.scaloi.util.probability

class SlotFillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("fillMix")

        val combinationMatrix3x3: CombinationMatrix3x5 = if (isUseWild && probability(20)) {
            log("fillMix use wild")
            Combination3x5.MixWithWild
        } else Combination3x5.Mix

        val index = (0..combinationMatrix3x3.matrixList.lastIndex).shuffled().first()
        val matrix3x3 = combinationMatrix3x3.matrixList[index].initialize()
        combinationMatrix3x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix3x3.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix3x3 = listOf<CombinationMatrix3x5>(
            Combination3x5.WinMonochrome,
            Combination3x5.WinMonochromeWithWild,
            Combination3x5.WinColorful,
            Combination3x5.WinColorfulWithWild,
        ).shuffled().first()

        val index = (0..combinationMatrix3x3.matrixList.lastIndex).shuffled().first()
        val matrix3x3 = combinationMatrix3x3.matrixList[index].initialize()
        combinationMatrix3x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix3x3.generateSlot(index) }

        with(matrix3x3) {
            winFillResult = winSlotItemSet?.let { FillResult(it, intersectionList!!) }

            log("""
                
                scheme           = $scheme
                winSet           = $winSlotItemSet
                intersectionList = $intersectionList
                """
            )
        }
    }

    private fun CombinationMatrix3x5.logMatrix(name: Int) {
        val combinationEnumName = when (this) {
            is Combination3x5.Mix                   -> "Mix $name"
            is Combination3x5.MixWithWild           -> "Mix WILD $name"
            is Combination3x5.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination3x5.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination3x5.WinColorful           -> "Win COLORFUL $name"
            is Combination3x5.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
            else                                    -> "Noname"
        }
        log(combinationEnumName)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            FillStrategy.MIX -> fillMix()
            FillStrategy.WIN -> fillWin()
        }
    }

}