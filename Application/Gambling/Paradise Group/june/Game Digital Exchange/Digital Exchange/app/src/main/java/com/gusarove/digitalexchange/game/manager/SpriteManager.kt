package com.gusarove.digitalexchange.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gusarove.digitalexchange.game.utils.region

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
        GAME( TextureAtlasData("atlas/game.atlas") ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        GARAG(    TextureData("textures/garag.png")    ),
        ESHLE(    TextureData("textures/eshle.png")    ),
        SLOW(     TextureData("textures/slow.png")     ),
        STATICA(  TextureData("textures/statica.png")  ),
        PURINAONE(TextureData("textures/purinaone.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        GARAG(EnumTexture.GARAG.data.texture.region),
        ESHLE(EnumTexture.ESHLE.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BK(       EnumAtlas.GAME.data.atlas.findRegion("bk")       ),
        DOM_DF(   EnumAtlas.GAME.data.atlas.findRegion("dom_df")   ),
        DOM_PRS(  EnumAtlas.GAME.data.atlas.findRegion("dom_prs")  ),
        LESS(     EnumAtlas.GAME.data.atlas.findRegion("less")     ),
        MORE(     EnumAtlas.GAME.data.atlas.findRegion("more")     ),
        PADAE(    EnumAtlas.GAME.data.atlas.findRegion("padae")    ),
        PANELJAN( EnumAtlas.GAME.data.atlas.findRegion("paneljan") ),
        PONEL(    EnumAtlas.GAME.data.atlas.findRegion("ponel")    ),
        ROSTE(    EnumAtlas.GAME.data.atlas.findRegion("roste")    ),
        STAT_DDF( EnumAtlas.GAME.data.atlas.findRegion("stat_ddf") ),
        STATA_CK( EnumAtlas.GAME.data.atlas.findRegion("stata_ck") ),
        CBFAK(    EnumAtlas.GAME.data.atlas.findRegion("cbfak")    ),
        CBGUIK(   EnumAtlas.GAME.data.atlas.findRegion("cbguik")   ),
        POL_DEF(  EnumAtlas.GAME.data.atlas.findRegion("pol_def")  ),
        POL_PRESS(EnumAtlas.GAME.data.atlas.findRegion("pol_press")),

        SLOW(      EnumTexture.SLOW.data.texture.region             ),
        STATICA(   EnumTexture.STATICA.data.texture.region          ),
        PURINAONE( EnumTexture.PURINAONE.data.texture.region        ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
      //  CARTOCHKI( List(9) {  EnumAtlas.CARTOCHKI.data.atlas.findRegion("${it.inc()}") }),
      //  COINS( List(18) {     EnumAtlas.COINS.data.atlas.findRegion("${it.inc()}")     }),
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