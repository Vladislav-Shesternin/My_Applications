package com.favsport.slots.utils.slot

import com.badlogic.gdx.graphics.Texture

data class SlotItem(
    val id: Int,
    var price: Int,
    val item: Texture,
)