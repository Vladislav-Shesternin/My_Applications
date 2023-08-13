package com.forvovim.smartconverter.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathGilroy_Medium   = "TTF/Gilroy-Medium.ttf"
    private const val pathGilroy_Regular  = "TTF/Gilroy-Regular.ttf"
    private const val pathGilroy_Semibold = "TTF/Gilroy-Semibold.ttf"

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
            loadableListFont.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadableListFont.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }

    object GilReg: IFont {
        val font_24 = FontTTFData("GilReg_24", getLoaderParameter(pathGilroy_Regular) { size = 24 })
        val font_27 = FontTTFData("GilReg_27", getLoaderParameter(pathGilroy_Regular) { size = 27 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_24, font_27)
    }

    object GilSemBold: IFont {
        val font_32 = FontTTFData("GilSemBold_32", getLoaderParameter(pathGilroy_Semibold) { size = 32 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_32)
    }

    object GilMedium: IFont {
        val font_30 = FontTTFData("GilMedium_30", getLoaderParameter(pathGilroy_Medium) { size = 30 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_30)
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