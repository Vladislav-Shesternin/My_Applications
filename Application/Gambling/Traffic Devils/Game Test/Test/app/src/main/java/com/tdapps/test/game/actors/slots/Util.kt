package com.tdapps.test.game.actors.slots

import com.badlogic.gdx.graphics.g2d.TextureRegion

//data class SpinResult(
//    val winSlotItemSet: Set<SlotItem>?,
//)

data class SlotItem(
    val id       : Int,
    val region   : TextureRegion,
)

enum class FillStrategy {
    MIX, WIN
}