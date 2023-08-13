package com.dankom.financialtracker.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathGilroySemibold   = "TTF/Gilroy-Semibold.ttf"
    private const val pathPoppinsMedium    = "TTF/Poppins-Medium.ttf"
    private const val pathPoppinsRegular   = "TTF/Poppins-Regular.ttf"
    private const val pathPoppinsSemiBold  = "TTF/Poppins-SemiBold.ttf"
    private const val pathProstoOneRegular = "TTF/ProstoOne-Regular.ttf"

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

    object ProstoOneRegular: IFont {
        val font_34 = FontTTFData("ProstoOneRegular_34", getLoaderParameter(pathProstoOneRegular) { size = 34 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_34)
    }
    object GilroySemibold: IFont {
        val font_27 = FontTTFData("GilroySemibold_27", getLoaderParameter(pathGilroySemibold) { size = 27 })
        val font_45 = FontTTFData("GilroySemibold_45", getLoaderParameter(pathGilroySemibold) { size = 45 })

        val fontP_27 = FontTTFData("p_27", getLoaderParameter(pathGilroySemibold) { size = 27 })
        val fontP_52 = FontTTFData("p_52", getLoaderParameter(pathGilroySemibold) { size = 52 })
        val fontP_21 = FontTTFData("p_21", getLoaderParameter(pathGilroySemibold) { size = 21 })
        val fontP_28 = FontTTFData("p_28", getLoaderParameter(pathGilroySemibold) { size = 28 })


        override val values: List<FontTTFData>
            get() = super.values + listOf(font_27, font_45,    fontP_27, fontP_52, fontP_21, fontP_28)
    }
    object PoppinsSemiBold: IFont {
        val font_27 = FontTTFData("PoppinsSemiBold_27", getLoaderParameter(pathPoppinsSemiBold) { size = 27 })
        val font_52 = FontTTFData("PoppinsSemiBold_52", getLoaderParameter(pathPoppinsSemiBold) { size = 52 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_27, font_52)
    }
    object PoppinsRegular: IFont {
        val font_21 = FontTTFData("PoppinsRegular_21", getLoaderParameter(pathPoppinsRegular) { size = 21 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_21)
    }
    object PoppinsMedium: IFont {
        val font_28 = FontTTFData("PoppinsMedium_28", getLoaderParameter(pathPoppinsMedium) { size = 28 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_28)
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