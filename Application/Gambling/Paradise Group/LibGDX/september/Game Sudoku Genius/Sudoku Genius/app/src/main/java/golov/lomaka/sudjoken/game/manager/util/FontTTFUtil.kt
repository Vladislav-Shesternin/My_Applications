package golov.lomaka.sudjoken.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import golov.lomaka.sudjoken.game.manager.FontTTFManager

class FontTTFUtil {

    private val pathInter_Regular = "font/Inter-Regular.ttf"

    val fontInterRegular = Inter().Regular()

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
        inner class Regular: IFont {
            val font_58 = FontTTFManager.FontTTFData("Regular 58",getLoaderParameter(pathInter_Regular) { size = 58 })
            val font_38 = FontTTFManager.FontTTFData("Regular 38",getLoaderParameter(pathInter_Regular) { size = 38 })

            override val values = listOf(font_58, font_38)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}