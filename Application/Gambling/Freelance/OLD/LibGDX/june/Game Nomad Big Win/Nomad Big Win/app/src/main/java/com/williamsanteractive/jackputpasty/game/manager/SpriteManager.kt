package com.williamsanteractive.jackputpasty.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.williamsanteractive.jackputpasty.game.utils.region

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
        BACKGROUND( TextureData("textures/background.png") ),
        GIRL(       TextureData("textures/girl.png")       ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND( EnumTexture.BACKGROUND.data.texture.region        ),
        GIRL(       EnumTexture.GIRL.data.texture.region    ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        CIRIL(         EnumAtlas.GAME.data.atlas.findRegion("ciril")         ),
        COSTAET(       EnumAtlas.GAME.data.atlas.findRegion("costaet")       ),
        GO_GO(         EnumAtlas.GAME.data.atlas.findRegion("go_go")         ),
        OFFA(          EnumAtlas.GAME.data.atlas.findRegion("offa")          ),
        ONNA(          EnumAtlas.GAME.data.atlas.findRegion("onna")          ),
        PL_DE(         EnumAtlas.GAME.data.atlas.findRegion("pl_de")         ),
        PREDISABLE(    EnumAtlas.GAME.data.atlas.findRegion("predisable")    ),
        RECOTAPANELKA( EnumAtlas.GAME.data.atlas.findRegion("recotapanelka") ),
        SO_SO(         EnumAtlas.GAME.data.atlas.findRegion("so_so")         ),
        XITE(          EnumAtlas.GAME.data.atlas.findRegion("xite")          ),
    }


    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMKES( List(5) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}") }),
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