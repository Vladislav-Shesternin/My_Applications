package com.jungle.jumping.bird.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

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
        GAME(  TextureAtlasData("atlas/game.atlas")  ),
    }

//    enum class EnumTexture(override val data: TextureData): ITexture {
//        BACKGROUND(TextureData("textures/background.png")),
//    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACKGROUND( EnumAtlas.GAME.data.atlas.findRegion("background") ),
        BALK(       EnumAtlas.GAME.data.atlas.findRegion("balk")       ),
        BIRD(       EnumAtlas.GAME.data.atlas.findRegion("bird")       ),
        PANEL(      EnumAtlas.GAME.data.atlas.findRegion("panel")      ),
        PDFKA(      EnumAtlas.GAME.data.atlas.findRegion("pdfka")      ),
        PLAY_DEF(   EnumAtlas.GAME.data.atlas.findRegion("play_def")   ),
        PLAY_PRESS( EnumAtlas.GAME.data.atlas.findRegion("play_press") ),
    }

//    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
//        MAN( List(21) { EnumAtlas.MAN.data.atlas.findRegion("man (${it.inc()})") }),
//        FIRE(List(10) { EnumAtlas.FIRE.data.atlas.findRegion("fire (${it.inc()})") }),
//    }



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