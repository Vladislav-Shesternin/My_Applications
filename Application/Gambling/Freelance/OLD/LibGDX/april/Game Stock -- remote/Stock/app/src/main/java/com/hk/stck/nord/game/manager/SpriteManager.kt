package com.hk.stck.nord.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hk.stck.nord.game.utils.region

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
        GAME(TextureAtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("textures/background.png")),
        B(TextureData("textures/b.png")),
    }



    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BIG_DEF(     EnumAtlas.GAME.data.atlas.findRegion("big_def")     ),
        BIG_PRESS(   EnumAtlas.GAME.data.atlas.findRegion("big_press")   ),
        MINI_DEF(    EnumAtlas.GAME.data.atlas.findRegion("mini_def")    ),
        MINI_PRESS(  EnumAtlas.GAME.data.atlas.findRegion("mini_press")  ),
        STOCK(       EnumAtlas.GAME.data.atlas.findRegion("stock")       ),
        STOCK_DEFF(  EnumAtlas.GAME.data.atlas.findRegion("stock_deff")  ),
        STOCK_PRESS( EnumAtlas.GAME.data.atlas.findRegion("stock_press") ),

        B(EnumTexture.B.data.texture.region),
    }


    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
      //  MAN(     List(21) { EnumAtlas.MAN.data.atlas.findRegion("man (${it.inc()})") }),
      //  FIRE(    List(10) { EnumAtlas.FIRE.data.atlas.findRegion("fire (${it.inc()})") }),
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