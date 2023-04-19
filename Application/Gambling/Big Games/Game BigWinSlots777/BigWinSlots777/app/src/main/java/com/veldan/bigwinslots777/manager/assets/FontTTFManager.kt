package com.veldan.bigwinslots777.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.bigwinslots777.manager.assets.util.FontTTFData

object FontTTFManager {

    private const val pathNotoSans = "font/TTF/NotoSans.ttf"
    private const val pathRockwell = "font/TTF/Rockwell.ttf"
    private const val pathAmarante = "font/TTF/Amarante.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<FontTTFData>()



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
            loadListFont.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }



    object RockwellFont {
        val font_60  = FontTTFData("Rockwell_60", getLoaderParameter(pathRockwell) { size = 60 })

        val values = listOf<FontTTFData>(font_60)
    }

    object AmaranteFont: IFont {
        override val font_30  = FontTTFData("Amarante_30", getLoaderParameter(pathAmarante) { size = 30 })
        override val font_50  = FontTTFData("Amarante_50", getLoaderParameter(pathAmarante) { size = 50 })
        override val font_60  = FontTTFData("Amarante_60", getLoaderParameter(pathAmarante) { size = 60 })
        override val font_100 = FontTTFData("Amarante_100", getLoaderParameter(pathAmarante) { size = 100 })
    }

    object NotoSansFont: IFont {
        override val font_30  = FontTTFData("NotoSans_30", getLoaderParameter(pathNotoSans) { size = 30 })
        override val font_50  = FontTTFData("NotoSans_50", getLoaderParameter(pathNotoSans) { size = 50 })
        override val font_60  = FontTTFData("NotoSans_60", getLoaderParameter(pathNotoSans) { size = 60 })
        override val font_100 = FontTTFData("NotoSans_100", getLoaderParameter(pathNotoSans) { size = 100 })
    }



    interface IFont {
        val font_30: FontTTFData
        val font_50: FontTTFData
        val font_60: FontTTFData
        val font_100: FontTTFData

        val values get() = listOf<FontTTFData>(font_30, font_50, font_60, font_100)
    }
}