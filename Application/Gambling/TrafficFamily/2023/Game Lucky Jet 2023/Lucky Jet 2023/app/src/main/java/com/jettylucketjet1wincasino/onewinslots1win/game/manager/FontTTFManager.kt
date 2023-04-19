package com.jettylucketjet1wincasino.onewinslots1win.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathSpaceGrotesk_Bold    = "TTF/SpaceGrotesk-Bold.ttf"
    private const val pathSpaceGrotesk_Regular = "TTF/SpaceGrotesk-Regular.ttf"

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


    object BoldFont : IFont {
        val font_35 = FontTTFData("Bold_35", getLoaderParameter(pathSpaceGrotesk_Bold) { size = 35 })
        val font_21 = FontTTFData("Bold_21", getLoaderParameter(pathSpaceGrotesk_Bold) { size = 21 })
        val font_19 = FontTTFData("Bold_19", getLoaderParameter(pathSpaceGrotesk_Bold) { size = 19 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_35, font_21, font_19)
    }

    object RegularFont : IFont {
        val font_35 = FontTTFData("Regular_35", getLoaderParameter(pathSpaceGrotesk_Regular) { size = 35 })
        val font_19 = FontTTFData("Regular_19", getLoaderParameter(pathSpaceGrotesk_Regular) { size = 19 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_35, font_19)
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