package com.hk.stck.nord.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathAlegreyaSansSC_Bold      = "TTF/AlegreyaSansSC-Bold.ttf"
    private const val pathAlegreyaSansSC_ExtraBold = "TTF/AlegreyaSansSC-ExtraBold.ttf"
    private const val pathAlegreyaSansSC_Regular   = "TTF/AlegreyaSansSC-Regular.ttf"


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


    object AlegreyaSansSC_Regular : IFont {
        val font_71 = FontTTFData("Regular_71", getLoaderParameter(pathAlegreyaSansSC_Regular) { size = 71 })
        val font_65 = FontTTFData("Regular_65", getLoaderParameter(pathAlegreyaSansSC_Regular) { size = 65 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_71, font_65)
    }

    object AlegreyaSansSC_ExtraBold : IFont {
        val font_65 = FontTTFData("ExtraBold_65", getLoaderParameter(pathAlegreyaSansSC_ExtraBold) { size = 65 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_65)
    }

    object AlegreyaSansSC_Bold : IFont {
        val font_60 = FontTTFData("Bold_60", getLoaderParameter(pathAlegreyaSansSC_Bold) { size = 60 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_60)
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