package com.veldan.base.box2d.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathInter_ExtraBold = "TTF/Inter-ExtraBold.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadableFontList = mutableListOf<FontTTFData>()

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
            loadableFontList.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadableFontList.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }



    object Inter {
        object ExtraBold: IFont {
            val font_100 = FontTTFData("Inter_ExtraBold_100", getLoaderParameter(pathInter_ExtraBold) { size = 100 })
            val font_50  = FontTTFData("Inter_ExtraBold_50" , getLoaderParameter(pathInter_ExtraBold) { size = 50  })
            val font_40  = FontTTFData("Inter_ExtraBold_40" , getLoaderParameter(pathInter_ExtraBold) { size = 40  })
            val font_30  = FontTTFData("Inter_ExtraBold_30" , getLoaderParameter(pathInter_ExtraBold) { size = 30  })
            val font_25  = FontTTFData("Inter_ExtraBold_25" , getLoaderParameter(pathInter_ExtraBold) { size = 25  })

            override val values: List<FontTTFData>
                get() = super.values + listOf(font_100, font_50)
        }
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