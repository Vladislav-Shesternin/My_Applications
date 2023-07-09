package crapto.makasinik.cryptoinsightspro.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

object FontTTFManager {

    private const val pathGilroy_Bold    = "TTF/Gilroy-Bold.ttf"
    private const val pathGilroy_Medium  = "TTF/Gilroy-Medium.ttf"
    private const val pathGilroy_Regular = "TTF/Gilroy-Regular.ttf"

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
            characters  = FreeTypeFontGenerator.DEFAULT_CHARS// + ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
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

    object GilMed: IFont {
        val font_30 = FontTTFData("GilMed_30", getLoaderParameter(pathGilroy_Medium) { size = 30 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_30)
    }

    object GilReg: IFont {
        val font_45 = FontTTFData("GilReg_45", getLoaderParameter(pathGilroy_Regular) { size = 45 })
        val font_26 = FontTTFData("GilReg_26", getLoaderParameter(pathGilroy_Regular) { size = 26 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_45, font_26)
    }

    object GilBold: IFont {
        val font_22 = FontTTFData("GilBold_22", getLoaderParameter(pathGilroy_Bold) { size = 22 })

        override val values: List<FontTTFData>
            get() = super.values + listOf(font_22)
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