package com.veldan.fantasticslots.actors.slot

import com.badlogic.gdx.graphics.Texture

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
    val bonus         : Bonus?,
)

data class CheckResult(
    val checkSlotList: List<CheckSlot>,
    val slotItem     : Set<SlotItem>,
)

data class CheckSlot(
    val index  : Int,
    val rowList: List<Int>,
)

data class SlotItem(
    val id         : Int,
    var priceFactor: Float,
    val texture    : Texture,
)



enum class Bonus {
    SUPER_GAME,
}

enum class Combination(val slotCount: Int) {
    _3(3), _4(4), _5(5)
}


sealed class FillStrategy {
    object RANDOM: FillStrategy()
    object WIN   : FillStrategy()
    object SUPER : FillStrategy()
}