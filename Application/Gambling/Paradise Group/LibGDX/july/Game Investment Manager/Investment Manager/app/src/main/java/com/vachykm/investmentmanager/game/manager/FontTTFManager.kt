package com.vachykm.investmentmanager.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathGilroyBold      = "TTF/Gilroy-Bold.ttf"
    private const val pathGilroyMedium    = "TTF/Gilroy-Medium.ttf"
    private const val pathGilroySemibold  = "TTF/Gilroy-Semibold.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadableListFont = mutableListOf<FontTTFData>()

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
            characters  = FreeTypeFontGenerator.DEFAULT_CHARS + ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
            minFilter   = TextureFilter.Linear
            magFilter   = TextureFilter.Linear
            incremental = true
            parameters()
        }
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadableListFont.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadableListFont.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }

    object GilBold: IFont {
        val font_19 = FontTTFData("GilBold_19", getLoaderParameter(pathGilroyBold) { size = 19 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_19)
    }
    object GilMed: IFont {
        val font_62 = FontTTFData("GilMed_62", getLoaderParameter(pathGilroyMedium) { size = 62 })
        val font_25 = FontTTFData("GilMed_25", getLoaderParameter(pathGilroyMedium) { size = 25 })
        val font_22 = FontTTFData("GilMed_22", getLoaderParameter(pathGilroyMedium) { size = 22 })
        val font_19 = FontTTFData("GilMed_19", getLoaderParameter(pathGilroyMedium) { size = 19 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_62, font_25, font_22, font_19)
    }
    object GilSemBold: IFont {
        val font_28 = FontTTFData("GilSemBol_28", getLoaderParameter(pathGilroySemibold) { size = 28 })
        val font_25 = FontTTFData("GilSemBol_25", getLoaderParameter(pathGilroySemibold) { size = 25 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_28, font_25)
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