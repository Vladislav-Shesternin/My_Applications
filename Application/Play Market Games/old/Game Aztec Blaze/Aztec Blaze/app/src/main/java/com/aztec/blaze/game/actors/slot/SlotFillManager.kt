package com.aztec.blaze.game.actors.slot

import com.aztec.blaze.util.log
import com.aztec.blaze.util.probability

class SlotFillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("fillMix")

        val combinationMatrix3x3: CombinationMatrix5x5 = if (isUseWild && probability(20)) {
            log("fillMix use wild")
            Combination5x5.MixWithWild
        } else Combination5x5.Mix

        val index = (0..combinationMatrix3x3.matrixList.lastIndex).shuffled().first()
        val matrix3x3 = combinationMatrix3x3.matrixList[index].initialize()
        combinationMatrix3x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix3x3.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix3x3 = listOf<CombinationMatrix5x5>(
            Combination5x5.WinMonochrome,
            Combination5x5.WinMonochromeWithWild,
            Combination5x5.WinColorful,
            Combination5x5.WinColorfulWithWild,
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

    private fun CombinationMatrix5x5.logMatrix(name: Int) {
        val combinationEnumName = when (this) {
            is Combination5x5.Mix                   -> "Mix $name"
            is Combination5x5.MixWithWild           -> "Mix WILD $name"
            is Combination5x5.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination5x5.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination5x5.WinColorful           -> "Win COLORFUL $name"
            is Combination5x5.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
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