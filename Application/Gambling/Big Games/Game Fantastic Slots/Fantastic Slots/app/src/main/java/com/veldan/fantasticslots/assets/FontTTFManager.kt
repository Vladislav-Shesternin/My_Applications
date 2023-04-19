package com.veldan.fantasticslots.assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.fantasticslots.assets.util.FontTTFData

object FontTTFManager {

    private const val pathRobotoMono = "font/TTF/RobotoMono.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<IEnumFont>()



    private fun AssetManager.setLoaderTTF() {
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolverInternal))
        setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolverInternal))
    }



    private fun getLoaderParameter(
        fileName: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = fileName
        fontParameters.apply {
            minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
            magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
            parameters()
        }
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadListFont.onEach { load(it.data.name + ".ttf", BitmapFont::class.java, it.data.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.data.font = assetManager[it.data.name + ".ttf", BitmapFont::class.java] }
    }



    enum class EnumFont(override val data: FontTTFData): IEnumFont {
        ROBOTO_MONO_60(FontTTFData("RobotoMono_60", getLoaderParameter(pathRobotoMono) {
            size = 60
            incremental = true
        })),
        ROBOTO_MONO_30(FontTTFData("RobotoMono_30", getLoaderParameter(pathRobotoMono) {
            size = 30
            incremental = true
        })),
    }



    interface IEnumFont {
        val data: FontTTFData
    }
}