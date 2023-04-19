package com.veldan.bigwinslots777.actors.slot.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.Matrix3x4

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
    val bonus         : Bonus?,
)

data class FillResult(
    val winSlotItemList : List<SlotItem>,
    val intersectionList: List<Matrix3x4.Intersection>,
)

data class SlotItem(
    val id         : Int,
    var priceFactor: Float,
    val region     : TextureRegion,
)



enum class Bonus {
    MINI_GAME, SUPER_GAME
}

sealed class FillStrategy {
    object MIX            : FillStrategy()
    object WIN            : FillStrategy()
    object MINI           : FillStrategy()
    object SUPER          : FillStrategy()
    object MINI_WILD_WIN  : FillStrategy()
    object MINI_WILD_FAIL : FillStrategy()
    object SUPER_WILD_WIN : FillStrategy()
    object SUPER_WILD_FAIL: FillStrategy()
}