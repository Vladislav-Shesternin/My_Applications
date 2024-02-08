package com.invt.nard.app.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.invt.nard.app.game.utils.region

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
        GAME(   TextureAtlasData("atlas/game.atlas")   ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND( TextureData("textures/backgaraundik.png") ),
    }

    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }
    enum class GameRegion(override val region: TextureRegion): IRegion {
        BALLS(   EnumAtlas.GAME.data.atlas.findRegion("balls")   ),
        BITCOIN( EnumAtlas.GAME.data.atlas.findRegion("bitcoin") ),
        BLUE(    EnumAtlas.GAME.data.atlas.findRegion("blue")    ),
        CURRENT( EnumAtlas.GAME.data.atlas.findRegion("current") ),
        GREEN(   EnumAtlas.GAME.data.atlas.findRegion("green")   ),
        LEFT(    EnumAtlas.GAME.data.atlas.findRegion("left")    ),
        MUSIC_D( EnumAtlas.GAME.data.atlas.findRegion("music_d") ),
        MUSIC_NO(EnumAtlas.GAME.data.atlas.findRegion("music_no")),
        PLAY_D(  EnumAtlas.GAME.data.atlas.findRegion("play_d")  ),
        PLAY_P(  EnumAtlas.GAME.data.atlas.findRegion("play_p")  ),
        RIGHT(   EnumAtlas.GAME.data.atlas.findRegion("right")   ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        BALL( List(8) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}") }),
     //   FIRE(List(10) { EnumAtlas.FIRE.data.atlas.findRegion("fire (${it.inc()})") }),
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