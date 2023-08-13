package com.veldan.lbjt.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.lbjt.game.manager.FontTTFManager

class FontTTFUtil {

    private val pathInter_ExtraBold = "font/Inter-ExtraBold.ttf"
    private val pathInter_Black     = "font/Inter-Black.ttf"
    private val pathInter_Medium    = "font/Inter-Medium.ttf"

    val fontInterExtraBold = Inter().ExtraBold()
    val fontInterMedium    = Inter().Medium()
    val fontInterBlack     = Inter().Black()

    private fun getLoaderParameter(
        path: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = path
        fontParameters.apply {
            characters =
                FreeTypeFontGenerator.DEFAULT_CHARS + ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
            minFilter = Texture.TextureFilter.Linear
            magFilter = Texture.TextureFilter.Linear
            incremental = true
            parameters()
        }
    }

    inner class Inter {
        inner class ExtraBold: IFont {
            val font_100 = FontTTFManager.FontTTFData("Inter_ExtraBold_100", getLoaderParameter(pathInter_ExtraBold) { size = 100 })
            val font_50  = FontTTFManager.FontTTFData("Inter_ExtraBold_50",  getLoaderParameter(pathInter_ExtraBold) { size = 50 })
            val font_40  = FontTTFManager.FontTTFData("Inter_ExtraBold_40",  getLoaderParameter(pathInter_ExtraBold) { size = 40 })
            val font_30  = FontTTFManager.FontTTFData("Inter_ExtraBold_30",  getLoaderParameter(pathInter_ExtraBold) { size = 30 })
            val font_25  = FontTTFManager.FontTTFData("Inter_ExtraBold_25",  getLoaderParameter(pathInter_ExtraBold) { size = 25 })

            override val values = listOf(font_100, font_50, font_40, font_30, font_25)
        }
        inner class Medium: IFont {
            val font_30 = FontTTFManager.FontTTFData("Inter_Medium_30", getLoaderParameter(pathInter_Medium) { size = 30 })

            override val values = listOf(font_30)
        }
        inner class Black: IFont {
            val font_25 = FontTTFManager.FontTTFData("Inter_Black_25",getLoaderParameter(pathInter_Black) { size = 25 })

            override val values = listOf(font_25)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}