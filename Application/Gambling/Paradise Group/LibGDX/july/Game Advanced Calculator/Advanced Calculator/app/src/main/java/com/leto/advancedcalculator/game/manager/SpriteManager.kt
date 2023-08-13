package com.leto.advancedcalculator.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.leto.advancedcalculator.game.utils.region

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
        LOADER(TextureAtlasData("atlas/loader.atlas")),
        CIRCLE(TextureAtlasData("atlas/cick.atlas")  ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        text(TextureData("textures/text.png")),
    }


    enum class SRegion(override val region: TextureRegion): IRegion {
        TEXT(EnumTexture.text.data.texture.region),

    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        ALIBABA( EnumAtlas.GAME.data.atlas.findRegion("alibaba") ),
        BB(      EnumAtlas.GAME.data.atlas.findRegion("bb")      ),
        DUSA(    EnumAtlas.GAME.data.atlas.findRegion("dusa")    ),
        HIMAN(   EnumAtlas.GAME.data.atlas.findRegion("himan")   ),
        NAZAR(   EnumAtlas.GAME.data.atlas.findRegion("nazar")   ),
        SAMAK(   EnumAtlas.GAME.data.atlas.findRegion("samak")   ),
        URUS(    EnumAtlas.GAME.data.atlas.findRegion("urus")    ),

    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        CIRC(List(67)    { EnumAtlas.CIRCLE.data.atlas.findRegion("1 (${it.inc()})")}),
        LOADER(List(120) { EnumAtlas.LOADER.data.atlas.findRegion("1 (${it.inc()})")}),
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