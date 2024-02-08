package com.egyptian.rebirth.gremmy.actors.slot

import com.egyptian.rebirth.log
import com.egyptian.rebirth.probability

class SlotFillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("fillMix")

        val combinationMatrix3x3: CombinationMatrix6x4 = if (isUseWild && probability(20)) {
            log("fillMix use wild")
            Combination6x4.MixWithWild
        } else Combination6x4.Mix

        val index = (0..combinationMatrix3x3.matrixList.lastIndex).shuffled().first()
        val matrix3x3 = combinationMatrix3x3.matrixList[index].initialize()
        combinationMatrix3x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix3x3.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix3x3 = listOf<CombinationMatrix6x4>(
            Combination6x4.WinMonochrome,
            Combination6x4.WinMonochromeWithWild,
            Combination6x4.WinColorful,
            Combination6x4.WinColorfulWithWild,
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

    private fun CombinationMatrix6x4.logMatrix(name: Int) {
        val combinationEnumName = when (this) {
            is Combination6x4.Mix                   -> "Mix $name"
            is Combination6x4.MixWithWild           -> "Mix WILD $name"
            is Combination6x4.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination6x4.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination6x4.WinColorful           -> "Win COLORFUL $name"
            is Combination6x4.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
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