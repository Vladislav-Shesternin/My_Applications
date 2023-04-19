package com.veldan.junglego.assets.util

import com.badlogic.gdx.graphics.Texture

data class TextureData(
    val path: String,
) {
    lateinit var texture: Texture
}