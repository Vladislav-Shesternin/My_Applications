package com.fellinger.yeasman.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathKottaOne = "font/KottaOne.ttf"

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
            minFilter = TextureFilter.Linear
            magFilter = TextureFilter.Linear
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



    object KottaOneFont: IFont {
        override val font_100 = FontTTFData("KottaOne_100", getLoaderParameter(pathKottaOne) { size = 100 })
        override val font_150 = FontTTFData("KottaOne_150", getLoaderParameter(pathKottaOne) { size = 150 })
    }



    interface IFont {
        val font_100: FontTTFData
        val font_150: FontTTFData

        val values get() = listOf<FontTTFData>(font_100, font_150)
    }

    data class FontTTFData(
        val name: String,
        val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter,
    ) {
        lateinit var font: BitmapFont
    }
}