package mst.mysteryof.egyptest.game.actors.slots.slot4x3

import mst.mysteryof.egyptest.game.actors.slots.FillStrategy
import mst.mysteryof.egyptest.util.log

class SlotFillManager(
    private val slotList: List<Slot>,
    private val glowList: List<Glow>,
    private val slotItemContainer: SlotItemContainer
) {

    private fun fillMix() {
        log("fillMix")

        val combinationMatrix5x3: ICombinationMatrix4x3 = Combination4x3.Mix //if (Random().nextBoolean()) Combination5x3.Mix else Combination5x3.MixWithWild
        val matrix5x3Handler = Matrix4x3Handler(combinationMatrix5x3.getMatrixAndLog(), slotItemContainer)

        slotList.onEachIndexed { index, slot -> slot.listSlotItemWin = matrix5x3Handler.generateSlot(index) }
    }

    private fun fillWin() {
        log("fillWin")

        val combinationMatrix5x3: ICombinationMatrix4x3 = Combination4x3.Win

//            listOf<CombinationMatrix5x3>(
//            Combination5x3.WinMonochrome,
//            Combination5x3.WinMonochromeWithWild,
//        ).shuffled().first()

        val matrix5x3Handler = Matrix4x3Handler(combinationMatrix5x3.getMatrixAndLog(), slotItemContainer)

        slotList.onEachIndexed { index, slot -> slot.listSlotItemWin = matrix5x3Handler.generateSlot(index) }
        glowList.onEachIndexed { index, glow -> glow.listIndexWin    = matrix5x3Handler.generateGlow(index) }
    }

    private fun ICombinationMatrix4x3.getMatrixAndLog(): Matrix4x3 {
        val matrix5x3Index = (0..matrixList.lastIndex).random()
        val matrix5x3      = matrixList[matrix5x3Index]
        log("CombinationMatrix5x3: ${this::class.java.name.substringAfterLast('$')} | ${matrix5x3Index.inc()}")

        return matrix5x3
    }

    fun fill(fillStrategy: FillStrategy) {
        when (fillStrategy) {
            FillStrategy.MIX -> fillMix()
            FillStrategy.WIN -> fillWin()
        }
    }

}