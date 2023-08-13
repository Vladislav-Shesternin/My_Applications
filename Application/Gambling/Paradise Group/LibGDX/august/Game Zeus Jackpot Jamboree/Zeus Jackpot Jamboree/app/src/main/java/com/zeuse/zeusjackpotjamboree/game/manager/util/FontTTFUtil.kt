package com.zeuse.zeusjackpotjamboree.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.zeuse.zeusjackpotjamboree.game.manager.FontTTFManager

class FontTTFUtil {

    private val pathInter_Black = "font/Inter-Black.ttf"

    val fontInterBlack     = Inter().Black()

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

    inner class Inter {
        inner class Black: IFont {
            val font_68 = FontTTFManager.FontTTFData("Inter_Black_68",getLoaderParameter(pathInter_Black) { size = 68 })

            override val values = listOf(font_68)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}