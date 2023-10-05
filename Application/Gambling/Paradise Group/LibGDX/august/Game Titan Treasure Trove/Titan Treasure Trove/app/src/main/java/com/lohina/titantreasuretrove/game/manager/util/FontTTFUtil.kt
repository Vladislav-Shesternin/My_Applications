package com.lohina.titantreasuretrove.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.lohina.titantreasuretrove.game.manager.FontTTFManager

class FontTTFUtil {

    private val pathInder_Regular = "font/Inder-Regular.ttf"

    val fontInterBlack     = Inder().Regular()

    private fun getLoaderParameter(
        path: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = path
        fontParameters.apply {
            characters = FreeTypeFontGenerator.DEFAULT_CHARS// + ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
            minFilter = Texture.TextureFilter.Linear
            magFilter = Texture.TextureFilter.Linear
            incremental = true
            parameters()
        }
    }

    inner class Inder {
        inner class Regular: IFont {
            val font_30 = FontTTFManager.FontTTFData("Inder-Regular-30",getLoaderParameter(pathInder_Regular) { size = 30 })

            override val values = listOf(font_30)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}