package com.egyptian.rebirth.gremmy.actors.slot

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
)

data class FillResult(
    val winSlotItemSet  : Set<SlotItem>,
    val intersectionList: List<Matrix6x4.Intersection>,
)

data class SlotItem(
    val id       : Int,
    val region   : TextureRegion,
    var priceCoff: Float,
)

enum class FillStrategy {
    MIX, WIN
}