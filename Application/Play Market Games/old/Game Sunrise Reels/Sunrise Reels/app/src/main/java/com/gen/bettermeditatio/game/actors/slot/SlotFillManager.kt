package com.gen.bettermeditatio.game.actors.slot

import com.gen.bettermeditatio.log
import com.gen.bettermeditatio.probability

class SlotFillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("fillMix")

        val combinationMatrix3x3: CombinationMatrix3x1 = if (isUseWild && probability(20)) {
            log("fillMix use wild")
            Combination3x1.MixWithWild
        } else Combination3x1.Mix

        val index = (0..combinationMatrix3x3.matrixList.lastIndex).shuffled().first()
        val matrix3x3 = combinationMatrix3x3.matrixList[index].initialize()
        combinationMatrix3x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix3x3.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix3x3 = listOf<CombinationMatrix3x1>(
            Combination3x1.Win,
            Combination3x1.WinWithWild,
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

    private fun CombinationMatrix3x1.logMatrix(name: Int) {
        val combinationEnumName = when (this) {
            is Combination3x1.Mix         -> "Mix $name"
            is Combination3x1.MixWithWild -> "Mix WILD $name"
            is Combination3x1.Win         -> "Win $name"
            is Combination3x1.WinWithWild -> "Win WILD $name"
            else                          -> "Noname"
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