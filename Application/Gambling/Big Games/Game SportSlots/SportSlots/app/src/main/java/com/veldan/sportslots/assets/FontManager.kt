package com.veldan.sportslots.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.sportslots.assets.FontManager.FontEnum.*

object FontManager {

    private const val pathRubik = "font/RubikMonoOne-Regular.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    private val rubikDescriptor_33 = AssetDescriptor("rubik_33.ttf", BitmapFont::class.java)



    lateinit var rubik_33: BitmapFont



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



    private fun getRowdiesParameter64() = getLoaderParameter(pathRubik) { size = 33 }



    fun load(assetManager: AssetManager, assetEnum: FontEnum) {
        with(assetManager) {
            setLoaderTTF()
            load(assetEnum.descriptor.fileName, BitmapFont::class.java, assetEnum.parameters)
        }
    }

    fun init(assetManager: AssetManager, assetEnum: FontEnum) {
        with(assetManager) {
            when (assetEnum) {
                RUBIK_33 -> rubik_33 = get(assetEnum.descriptor)
            }
        }
    }

    fun loadAll(assetManager: AssetManager){
        FontEnum.values().onEach { load(assetManager, it) }
    }

    fun initAll(assetManager: AssetManager){
        FontEnum.values().onEach { init(assetManager, it) }
    }



    enum class FontEnum(
        val descriptor: AssetDescriptor<BitmapFont>,
        val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter
    ) {
        RUBIK_33(rubikDescriptor_33, getRowdiesParameter64()),
       // FONT_26(descriptor_26, getParameter26()),
       // FONT_17(descriptor_17, getParameter17()),
    }
}