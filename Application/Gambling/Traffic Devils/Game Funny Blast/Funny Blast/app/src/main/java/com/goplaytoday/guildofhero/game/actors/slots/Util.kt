package com.goplaytoday.guildofhero.game.actors.slots

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class SlotItem(
    val id       : Int,
    val region   : TextureRegion,
)

enum class FillStrategy {
    MIX, WIN
}