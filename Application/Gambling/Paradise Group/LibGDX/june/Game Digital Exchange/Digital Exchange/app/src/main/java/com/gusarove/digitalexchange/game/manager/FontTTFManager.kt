package com.gusarove.digitalexchange.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathLailaBold         = "TTF/Laila-Bold.ttf"
    private const val pathLailaRegular      = "TTF/Laila-Regular.ttf"
    private const val pathRobotoMonoRegular = "TTF/RobotoMono-Regular.ttf"

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

    object LeilaBold: IFont {
        val font_80 = FontTTFData("Bol_80", getLoaderParameter(pathLailaBold) { size = 80 })
        val font_35 = FontTTFData("Bol_35", getLoaderParameter(pathLailaBold) { size = 35 })
        val font_51 = FontTTFData("Bol_51", getLoaderParameter(pathLailaBold) { size = 51 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_80, font_35, font_51)
    }

    object LeilaRegular: IFont {
        val font_35 = FontTTFData("Reg_35", getLoaderParameter(pathLailaRegular) { size = 35 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_35)
    }

    object Roboto: IFont {
        val font_50 = FontTTFData("roboto", getLoaderParameter(pathRobotoMonoRegular) { size = 50 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_50)
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