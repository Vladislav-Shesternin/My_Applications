package com.veldan.junglego.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.junglego.assets.FontManager.EnumFont.*
import com.veldan.junglego.assets.util.FontData
import com.veldan.junglego.assets.util.TextureData

object FontManager {

    private const val pathRationale = "font/Rationale.ttf"

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
        parameters(fontParameters)
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadListFont.onEach { load(it.fontData.name + ".ttf", BitmapFont::class.java, it.fontData.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.fontData.font = assetManager[it.fontData.name + ".ttf", BitmapFont::class.java] }
    }



    enum class EnumFont(override val fontData: FontData): IEnumFont {
        RATIONALE_85(FontData("rationale_85", getLoaderParameter(pathRationale) { size = 85 })),
        RATIONALE_70(FontData("rationale_70", getLoaderParameter(pathRationale) { size = 70 })),
        RATIONALE_50(FontData("rationale_50", getLoaderParameter(pathRationale) { size = 50 })),
    }



    interface IEnumFont {
        val fontData: FontData
    }
}