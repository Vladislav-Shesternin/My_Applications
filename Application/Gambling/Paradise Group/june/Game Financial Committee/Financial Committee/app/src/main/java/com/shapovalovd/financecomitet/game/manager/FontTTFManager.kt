package com.shapovalovd.financecomitet.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathNunitoSans10ptRegular = "TTF/NunitoSans_10pt-Regular.ttf"
    private const val pathPoppinsMedium         = "TTF/Poppins-Medium.ttf"
    private const val pathPoppinsSemiBold       = "TTF/Poppins-SemiBold.ttf"

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

    object NunitoSans: IFont {
        val font_40 = FontTTFData("Reg_40", getLoaderParameter(pathNunitoSans10ptRegular) { size = 40 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_40)
    }

    object PopMedium: IFont {
        val font_15 = FontTTFData("PopMedium_15", getLoaderParameter(pathPoppinsMedium) { size = 15 })
        val font_20 = FontTTFData("PopMedium_20", getLoaderParameter(pathPoppinsMedium) { size = 20 })
        val font_22 = FontTTFData("PopMedium_22", getLoaderParameter(pathPoppinsMedium) { size = 22 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_15, font_20, font_22)
    }

    object PopSemiBold: IFont {
        val font_34 = FontTTFData("PopSemiBold_34", getLoaderParameter(pathPoppinsSemiBold) { size = 34 })
        val font_27 = FontTTFData("PopSemiBold_27", getLoaderParameter(pathPoppinsSemiBold) { size = 27 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_27, font_34)
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