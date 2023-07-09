package com.zaykoa.investmentanalyzer.game.manager

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
    private const val pathPoppinsSemiBold = "TTF/Poppins-SemiBold.ttf"

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

    object PopSemBold: IFont {
        val font_48 = FontTTFData("PopSemBold_48", getLoaderParameter(pathPoppinsSemiBold) { size = 48 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_48)
    }

    object GilBold: IFont {
        val font_44 = FontTTFData("GilBold_44", getLoaderParameter(pathGilroyBold) { size = 44 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_44)
    }
    object GilMed: IFont {
        val font_37 = FontTTFData("GilMed_37", getLoaderParameter(pathGilroyMedium) { size = 37 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_37)
    }
    object GilSemBold: IFont {
        val font_51 = FontTTFData("GilSemBol_51", getLoaderParameter(pathGilroySemibold) { size = 51 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_51)
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