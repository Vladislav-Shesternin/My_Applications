package com.tsabekaa.finhelper.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathAnonymousProBold    = "TTF/AnonymousPro-Bold.ttf"
    private const val pathAnonymousProRegular = "TTF/AnonymousPro-Regular.ttf"

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

    object RegularFont: IFont {
        val font_40 = FontTTFData("Regular_40", getLoaderParameter(pathAnonymousProRegular) { size = 40 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_40)
    }

    object BoldFont: IFont {
        val font_20 = FontTTFData("Bold_20", getLoaderParameter(pathAnonymousProBold) { size = 20 })
        val font_35 = FontTTFData("Bold_35", getLoaderParameter(pathAnonymousProBold) { size = 35 })
        val font_40 = FontTTFData("Bold_40", getLoaderParameter(pathAnonymousProBold) { size = 40 })
        val font_50 = FontTTFData("Bold_50", getLoaderParameter(pathAnonymousProBold) { size = 50 })
        val font_80 = FontTTFData("Bold_80", getLoaderParameter(pathAnonymousProBold) { size = 80 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_20, font_35, font_40, font_50, font_80)
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