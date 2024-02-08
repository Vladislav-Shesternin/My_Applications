package com.mesga.moolahit.game.actors.slot

import com.mesga.moolahit.log

class SlotFillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("fillMix")

        val combinationMatrix4x3: CombinationMatrix4x3 = /*if (isUseWild && probability(20)) {
            log("fillMix use wild")
            Combination6x4.MixWithWild
        } else*/ Combination4x3.Mix

        val index = (0..combinationMatrix4x3.matrixList.lastIndex).shuffled().first()
        val matrix4x3 = combinationMatrix4x3.matrixList[index].initialize()
        combinationMatrix4x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix4x3.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix4x3 = listOf<CombinationMatrix4x3>(
            Combination4x3.WinMonochrome,
            Combination4x3.WinColorful,
        ).shuffled().first()

        val index = (0..combinationMatrix4x3.matrixList.lastIndex).shuffled().first()
        val matrix4x3 = combinationMatrix4x3.matrixList[index].initialize()
        combinationMatrix4x3.logMatrix(index.inc())

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = matrix4x3.generateSlot(index) }

        with(matrix4x3) {
            winFillResult = winSlotItemSet?.let { FillResult(it, intersectionList!!) }

            log("""
                
                scheme           = $scheme
                winSet           = $winSlotItemSet
                intersectionList = $intersectionList
                """
            )
        }
    }

    private fun CombinationMatrix4x3.logMatrix(name: Int) {
        val combinationEnumName = when (this) {
            is Combination4x3.Mix                   -> "Mix $name"
            is Combination4x3.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination4x3.WinColorful           -> "Win COLORFUL $name"
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