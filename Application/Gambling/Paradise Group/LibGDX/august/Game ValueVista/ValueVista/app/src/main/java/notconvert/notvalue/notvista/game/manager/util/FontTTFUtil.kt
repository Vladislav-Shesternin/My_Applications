package notconvert.notvalue.notvista.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import notconvert.notvalue.notvista.game.manager.FontTTFManager

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
            val font_66 = FontTTFManager.FontTTFData("Inder-Regular-66",getLoaderParameter(pathInder_Regular) { size = 66 })

            override val values = listOf(font_66)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}