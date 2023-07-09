package com.veldan.gamebox2d.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathRobotoRegular      = "TTF/Roboto-Regular.ttf"
    private const val pathJosefinSansRegular = "TTF/JosefinSans-Regular.ttf"
    private const val pathJosefinSansBold    = "TTF/JosefinSans-Bold.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadableFontList = mutableListOf<FontTTFData>()

//    val fontText: IFont get() = when(Language.locale.language) {
//        "ru", "uk" -> NotoSansFont
//        else -> MerriweatherSansFont
//    }



    private fun AssetManager.setLoaderTTF() {
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolverInternal))
        setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolverInternal))
    }

    private fun getLoaderParameter(
        path: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = path
        fontParameters.apply {
            characters  = FreeTypeFontGenerator.DEFAULT_CHARS// + ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
            minFilter   = TextureFilter.Linear
            magFilter   = TextureFilter.Linear
            incremental = true
            parameters()
        }
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadableFontList.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadableFontList.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }



    object RobotoRegularFont: IFont {
        val font_75 = FontTTFData("Roboto_Regular_75", getLoaderParameter(pathRobotoRegular) { size = 75 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_75)
    }

    object JosefinRegularFont: IFont {
        val font_75 = FontTTFData("Josefin_Regular_75", getLoaderParameter(pathJosefinSansRegular) { size = 75 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_75)
    }

    object JosefinBoldFont: IFont {
        val font_75 = FontTTFData("Josefin_Bold_75", getLoaderParameter(pathJosefinSansBold) { size = 75 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_75)
    }



    interface IFont {
        val values get() = listOf<FontTTFData>()
    }


    data class FontTTFData(
        val name: String,
        val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter,
    ) {
        lateinit var font: BitmapFont
    }
}