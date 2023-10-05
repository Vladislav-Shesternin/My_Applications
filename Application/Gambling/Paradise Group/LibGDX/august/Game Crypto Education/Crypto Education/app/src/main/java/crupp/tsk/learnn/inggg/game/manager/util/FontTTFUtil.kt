package crupp.tsk.learnn.inggg.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import crupp.tsk.learnn.inggg.game.manager.FontTTFManager

class FontTTFUtil {

    private val pathGilroy_Bold = "font/Gilroy-Bold.ttf"

    val fontInterBlack     = Gilroy().Bold()

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

    inner class Gilroy {
        inner class Bold: IFont {
            val font_50 = FontTTFManager.FontTTFData("Bold-50",getLoaderParameter(pathGilroy_Bold) { size = 50 })

            override val values = listOf(font_50)
        }
    }

    interface IFont {
        val values: List<FontTTFManager.FontTTFData>
    }
}