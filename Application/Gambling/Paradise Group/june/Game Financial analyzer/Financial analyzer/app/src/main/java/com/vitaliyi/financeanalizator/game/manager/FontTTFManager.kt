package com.vitaliyi.financeanalizator.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathPoppins_Bold   = "TTF/Poppins-Bold.ttf"
    private const val pathRoboto_Light   = "TTF/Roboto-Light.ttf"
    private const val pathRoboto_Medium  = "TTF/Roboto-Medium.ttf"
    private const val pathRoboto_Regular = "TTF/Roboto-Regular.ttf"

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

    object PoppinsBold: IFont {
        val font_30 = FontTTFData("Poppins_Bold_30", getLoaderParameter(pathPoppins_Bold) { size = 30 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_30)
    }

    object RLight: IFont {
        val font_66 = FontTTFData("RLight_66", getLoaderParameter(pathRoboto_Light) { size = 66 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_66)
    }

    object RRegular: IFont {
        val font_66 = FontTTFData("RRegular_66", getLoaderParameter(pathRoboto_Regular) { size = 66 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_66)
    }

    object RMedium: IFont {
        val font_34 = FontTTFData("RMedium_34", getLoaderParameter(pathRoboto_Medium) { size = 34 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_34)
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