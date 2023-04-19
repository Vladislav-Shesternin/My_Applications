package com.pharhaslo.slo7.game.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.pharhaslo.slo7.game.assets.util.FontTTFData

object FontTTFManager {

    private const val pathSigmarOne = "font/TTF/SigmarOne.ttf"

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
        SIGMAR_ONE_53(FontTTFData("SigmarOne_53", getLoaderParameter(pathSigmarOne) {
            size = 53
        })),
        SIGMAR_ONE_45(FontTTFData("SigmarOne_45", getLoaderParameter(pathSigmarOne) {
            size = 45
        })),
        SIGMAR_ONE_35(FontTTFData("SigmarOne_35", getLoaderParameter(pathSigmarOne) {
            size = 35
        })),
    }



    interface IEnumFont {
        val data: FontTTFData
    }
}