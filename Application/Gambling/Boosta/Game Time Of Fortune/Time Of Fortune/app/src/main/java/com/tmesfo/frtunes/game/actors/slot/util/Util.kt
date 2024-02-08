package com.tmesfo.frtunes.game.actors.slot.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tmesfo.frtunes.game.actors.slot.util.combination._3x3.Matrix3x3

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
)

data class FillResult(
    val winSlotItemList : List<SlotItem>,
    val intersectionList: List<Matrix3x3.Intersection>,
)

data class SlotItem(
    val id         : Int,
    var priceFactor: Float,
    val region     : TextureRegion,
)



sealed class FillStrategy {
    object MIX            : FillStrategy()
    object WIN            : FillStrategy()
}