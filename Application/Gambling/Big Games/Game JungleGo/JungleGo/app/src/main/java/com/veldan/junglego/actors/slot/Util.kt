package com.veldan.junglego.actors.slot

import com.badlogic.gdx.graphics.Texture

data class SpinResult(
    val slotItemList: List<SlotItem>,
    val bonus: Bonus?
)

data class SlotItem(
    val id: Int,
    var price_factor: Float,
    val item: Texture,
)



enum class Bonus(var price: Long) {
    MINI_GAME(0),
    SUPER_GAME(0),
}