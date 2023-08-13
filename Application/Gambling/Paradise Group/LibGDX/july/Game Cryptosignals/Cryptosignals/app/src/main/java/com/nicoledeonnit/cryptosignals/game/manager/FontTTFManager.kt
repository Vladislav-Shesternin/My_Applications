package com.nicoledeonnit.cryptosignals.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathMontserrat_Light   = "TTF/Montserrat-Light.ttf"
    private const val pathMontserrat_Medium  = "TTF/Montserrat-Medium.ttf"
    private const val pathMontserrat_Regular = "TTF/Montserrat-Regular.ttf"

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

    object Regular: IFont {
        val font_25 = FontTTFData("regula_25", getLoaderParameter(pathMontserrat_Regular) { size = 25 })
        val font_72 = FontTTFData("regula_72", getLoaderParameter(pathMontserrat_Regular) { size = 72 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_25, font_72)
    }
    object Light: IFont {
        val font_42 = FontTTFData("light_42", getLoaderParameter(pathMontserrat_Light) { size = 42 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_42)
    }
    object Medium: IFont {
        val font_23 = FontTTFData("medium_23", getLoaderParameter(pathMontserrat_Medium) { size = 23 })
        val font_72 = FontTTFData("medium_72", getLoaderParameter(pathMontserrat_Medium) { size = 72 })
        val font_30 = FontTTFData("medium_30", getLoaderParameter(pathMontserrat_Medium) { size = 30 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_23, font_72, font_30)
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