package com.tsabekaa.finhelper.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tsabekaa.finhelper.game.utils.region

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
        GAME(    TextureAtlasData("atlas/game.atlas")    ),
        ITEMS(   TextureAtlasData("atlas/items.atlas")   ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND( TextureData("textures/backgroundick.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BEKAPPA(       EnumAtlas.GAME.data.atlas.findRegion("bekappa")       ),
        BIX(           EnumAtlas.GAME.data.atlas.findRegion("bix")           ),
        BIXIKID(       EnumAtlas.GAME.data.atlas.findRegion("bixikid")       ),
        BTN_DFP_CLICK( EnumAtlas.GAME.data.atlas.findRegion("btn_dfp_click") ),
        BTNZDF(        EnumAtlas.GAME.data.atlas.findRegion("btnzdf")        ),
        DETAILE(       EnumAtlas.GAME.data.atlas.findRegion("detaile")       ),
        DOWN(          EnumAtlas.GAME.data.atlas.findRegion("down")          ),
        GREEN_PANEL(   EnumAtlas.GAME.data.atlas.findRegion("green_panel")   ),
        ITEM(          EnumAtlas.GAME.data.atlas.findRegion("item")          ),
        KATALOG(       EnumAtlas.GAME.data.atlas.findRegion("katalog")       ),
        MASAKA(        EnumAtlas.GAME.data.atlas.findRegion("masaka")        ),
        MUZIKA_SAUNDA( EnumAtlas.GAME.data.atlas.findRegion("muzika_saunda") ),
        NEWS(          EnumAtlas.GAME.data.atlas.findRegion("news")          ),
        PIPKA(         EnumAtlas.GAME.data.atlas.findRegion("pipka")         ),
        RADUGA(        EnumAtlas.GAME.data.atlas.findRegion("raduga")        ),
        RAMKA(         EnumAtlas.GAME.data.atlas.findRegion("ramka")         ),
        SETTT(         EnumAtlas.GAME.data.atlas.findRegion("settt")         ),
        UP(            EnumAtlas.GAME.data.atlas.findRegion("up")            ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS( List(16) { EnumAtlas.ITEMS.data.atlas.findRegion("$it") }),
        NEWS( List(20) { EnumAtlas.ITEMS.data.atlas.findRegion("news ${it.inc()}") }),
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