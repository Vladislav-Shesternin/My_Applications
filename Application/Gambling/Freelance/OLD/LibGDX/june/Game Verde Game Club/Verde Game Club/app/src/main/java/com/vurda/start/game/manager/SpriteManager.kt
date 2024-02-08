package com.vurda.start.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vurda.start.game.utils.region

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
        GAME(  TextureAtlasData("atlas/game.atlas"  )),
        CARTKI(TextureAtlasData("atlas/cartki.atlas")),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("textures/menuska.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        AAAA(    EnumAtlas.GAME.data.atlas.findRegion("aaaa")    ),
        BBBB(    EnumAtlas.GAME.data.atlas.findRegion("bbbb")    ),
        BTN_DIS( EnumAtlas.GAME.data.atlas.findRegion("btn_dis") ),
        WIN(     EnumAtlas.GAME.data.atlas.findRegion("WIN")     ),

        BACK(    EnumAtlas.CARTKI.data.atlas.findRegion("back")  ),
    }


    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        _1(List(13) { EnumAtlas.CARTKI.data.atlas.findRegion("1-${it + 2}") }),
        _2(List(13) { EnumAtlas.CARTKI.data.atlas.findRegion("2-${it + 2}") }),
        _3(List(13) { EnumAtlas.CARTKI.data.atlas.findRegion("3-${it + 2}") }),
        _4(List(13) { EnumAtlas.CARTKI.data.atlas.findRegion("4-${it + 2}") }),
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