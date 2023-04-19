package com.veldan.junglego.assets.util

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

data class FontData(
    val name: String,
    val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter,
) {
    lateinit var font: BitmapFont
}