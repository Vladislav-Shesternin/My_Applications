package com.fotune.tiger.slotthighrino.game.actors.slots

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class SlotItem(
    val region: TextureRegion,
)

enum class FillStrategy {
    MIX, WIN
}