package com.veldan.gamebox2d.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.utils.region

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
        LOADER( TextureAtlasData("atlas/loader.atlas") ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        GRID( TextureData("textures/grid.png") ),
        LOADER( TextureData("textures/loader.png") ),
    }


    enum class GameRegion(override val region: TextureRegion): IRegion {
        VERTICAL(   EnumAtlas.GAME.data.atlas.findRegion("v")         ),
        HORIZONTAL( EnumAtlas.GAME.data.atlas.findRegion("h")         ),
        CIRCLE(     EnumAtlas.GAME.data.atlas.findRegion("circle")    ),
        PLATFORMA(  EnumAtlas.GAME.data.atlas.findRegion("platforma") ),
        USER(       EnumAtlas.GAME.data.atlas.findRegion("user")      ),

        GRID(EnumTexture.GRID.data.texture.region),

        LOADER(EnumTexture.LOADER.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        LOADER( List(101) { EnumAtlas.LOADER.data.atlas.findRegion("$it") }),
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