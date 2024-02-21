package com.rcks.scaloi.game.actors.slot

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
)

data class FillResult(
    val winSlotItemSet  : Set<SlotItem>,
    val intersectionList: List<Matrix3x5.Intersection>,
)

data class SlotItem(
    val id       : Int,
    val region   : TextureRegion,
    var priceCoff: Float,
)

enum class FillStrategy {
    MIX, WIN
}