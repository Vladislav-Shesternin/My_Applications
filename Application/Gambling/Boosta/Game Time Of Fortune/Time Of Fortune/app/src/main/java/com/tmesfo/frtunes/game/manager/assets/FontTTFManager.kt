package com.tmesfo.frtunes.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.tmesfo.frtunes.game.manager.assets.util.FontTTFData

object FontTTFManager {

    private const val pathNotoSans     = "font/TTF/NotoSans.ttf"
    private const val pathAbrilFatface = "font/TTF/AbrilFatface.ttf"

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


    object AbrilFatfaceFont: IFont {
        override val font_70 = FontTTFData("AbrilFatface_70", getLoaderParameter(pathAbrilFatface) { size = 70 })
        override val font_80 = FontTTFData("AbrilFatface_80", getLoaderParameter(pathAbrilFatface) { size = 80 })

        override val values: List<FontTTFData>
            get() = super.values + font_80
    }

    object NotoSansFont: IFont {
        override val font_70  = FontTTFData("NotoSans_70", getLoaderParameter(pathNotoSans) { size = 70 })
        override val font_80  = FontTTFData("NotoSans_80", getLoaderParameter(pathNotoSans) { size = 80 })
    }



    interface IFont {
        val font_70: FontTTFData
        val font_80: FontTTFData

        val values get() = listOf<FontTTFData>(font_70, font_80)
    }
}