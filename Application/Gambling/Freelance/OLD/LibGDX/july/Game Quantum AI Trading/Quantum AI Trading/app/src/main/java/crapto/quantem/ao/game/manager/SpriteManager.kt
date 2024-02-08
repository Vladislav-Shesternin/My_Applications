package crapto.quantem.ao.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import crapto.quantem.ao.game.utils.region

object SpriteManager {

    var loadableAtlasList   = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun loadAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
    }

    fun loadTexture(assetManager: AssetManager) {
        loadableTextureList.onEach {
            assetManager.load(it.data.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
    }

    fun initTexture(assetManager: AssetManager) {
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }

    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        GAME(       TextureAtlasData("atlas/game.atlas")       ),
        ACCO(       TextureAtlasData("atlas/account.atlas")    ),
        BITOK(      TextureAtlasData("atlas/bitcoin.atlas")    ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BARODADA(     TextureData("textures/barodada.png")     ),
        LODERKAD(     TextureData("textures/loder.png")        ),
        KOSHEL(       TextureData("textures/koshel.png")       ),
        SETTINGESSAK( TextureData("textures/settingessak.png") ),
        PRAVILA(      TextureData("textures/pravila.png")      ),
        HOME(         TextureData("textures/home.png")         ),
        PAD(         TextureData("textures/pad.png")         ),
        ROS(         TextureData("textures/ros.png")         ),
        VAVKA(         TextureData("textures/vavka.png")         ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BARODADA(EnumTexture.BARODADA.data.texture.region),
        LODERKAD(EnumTexture.LODERKAD.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        ADD_TOKEN(    EnumAtlas.GAME.data.atlas.findRegion("add_token")    ),
        BACK(         EnumAtlas.GAME.data.atlas.findRegion("back")         ),
        CB_DIFUSOR(   EnumAtlas.GAME.data.atlas.findRegion("cb_difusor")   ),
        CB_PARAMOUNT( EnumAtlas.GAME.data.atlas.findRegion("cb_paramount") ),
        DEFFIC(       EnumAtlas.GAME.data.atlas.findRegion("deffic")       ),
        HOM_DEF(      EnumAtlas.GAME.data.atlas.findRegion("hom_def")      ),
        HOMI(         EnumAtlas.GAME.data.atlas.findRegion("homi")         ),
        LODER(        EnumAtlas.GAME.data.atlas.findRegion("loder")        ),
        LOGO(         EnumAtlas.GAME.data.atlas.findRegion("logo")         ),
        MARKEK(       EnumAtlas.GAME.data.atlas.findRegion("markek")       ),
        PALAN(        EnumAtlas.GAME.data.atlas.findRegion("palan")        ),
        PR_TEXT(      EnumAtlas.GAME.data.atlas.findRegion("pr_text")      ),
        PR_TR(        EnumAtlas.GAME.data.atlas.findRegion("pr_tr")        ),
        PRESSIC(      EnumAtlas.GAME.data.atlas.findRegion("pressic")      ),
        SETTI(        EnumAtlas.GAME.data.atlas.findRegion("setti")        ),
        SETTICKKK(    EnumAtlas.GAME.data.atlas.findRegion("settickkk")    ),

        KOSHEL(          EnumTexture.KOSHEL.data.texture.region          ),
        SETTINGESSAK(    EnumTexture.SETTINGESSAK.data.texture.region    ),
        PRAVILA(         EnumTexture.PRAVILA.data.texture.region         ),
        HOME(            EnumTexture.HOME.data.texture.region            ),
        PAD(            EnumTexture.PAD.data.texture.region            ),
        ROS(            EnumTexture.ROS.data.texture.region            ),
        VAVKA(            EnumTexture.VAVKA.data.texture.region            ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
       ACCOUNT( List(6) {  EnumAtlas.ACCO.data.atlas.findRegion("${it.inc()}") }),
       BITOK( List(18) {  EnumAtlas.BITOK.data.atlas.findRegion("${it.inc()}") }),
    }



    interface IAtlas {
        val data: TextureAtlasData
    }

    interface ITexture {
        val data: TextureData
    }

    interface IRegion {
        val region: TextureRegion
    }

    interface IRegionList {
        val regionList: List<TextureRegion>
    }

    data class TextureAtlasData(
        val path: String,
    ) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(
        val path: String,
    ) {
        lateinit var texture: Texture
    }

}