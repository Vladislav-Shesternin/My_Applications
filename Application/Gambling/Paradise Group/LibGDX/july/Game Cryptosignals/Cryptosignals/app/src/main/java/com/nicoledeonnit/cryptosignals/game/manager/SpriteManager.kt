package com.nicoledeonnit.cryptosignals.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.nicoledeonnit.cryptosignals.game.utils.region

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
        PARA(   TextureAtlasData("atlas/para.atlas")),

    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        garfada(TextureData("textures/garfada.png")),
        telekan(TextureData("textures/telekan.png")),
    }



    enum class Saprano(override val region: TextureRegion): IRegion {
        garfada(EnumTexture.garfada.data.texture.region),
    }

    enum class Palas(override val region: TextureRegion): IRegion {
        ALL(       EnumAtlas.GAME.data.atlas.findRegion("all")       ),
        CHASIKI(   EnumAtlas.GAME.data.atlas.findRegion("chasiki")   ),
        CHEKER(    EnumAtlas.GAME.data.atlas.findRegion("cheker")    ),
        DVA_PALCA( EnumAtlas.GAME.data.atlas.findRegion("dva_palca") ),
        HOT(       EnumAtlas.GAME.data.atlas.findRegion("hot")       ),
        MANUG(     EnumAtlas.GAME.data.atlas.findRegion("manug")     ),
        OD(        EnumAtlas.GAME.data.atlas.findRegion("od")        ),
        OH(        EnumAtlas.GAME.data.atlas.findRegion("oh")        ),
        OM(        EnumAtlas.GAME.data.atlas.findRegion("om")        ),
        OW(        EnumAtlas.GAME.data.atlas.findRegion("ow")        ),
        OY(        EnumAtlas.GAME.data.atlas.findRegion("oy")        ),
        P24HOUR(   EnumAtlas.GAME.data.atlas.findRegion("p24hour")   ),
        PALANKAGA( EnumAtlas.GAME.data.atlas.findRegion("palankaga") ),
        PEREDACHA( EnumAtlas.GAME.data.atlas.findRegion("peredacha") ),
        PODIM(     EnumAtlas.GAME.data.atlas.findRegion("podim")     ),
        POTERI(    EnumAtlas.GAME.data.atlas.findRegion("poteri")    ),
        PRIBIL(    EnumAtlas.GAME.data.atlas.findRegion("pribil")    ),
        PRODOZ(    EnumAtlas.GAME.data.atlas.findRegion("prodoz")    ),

        telekan(EnumTexture.telekan.data.texture.region),
    }

    enum class FanariK(override val regionList: List<TextureRegion>) : IRegionList {
        LOB(List(6) { EnumAtlas.PARA.data.atlas.findRegion("${it.inc()}")}),
        GRA(List(6) { EnumAtlas.PARA.data.atlas.findRegion("gr${it.inc()}")}),
        SAF(List(6) { EnumAtlas.PARA.data.atlas.findRegion("1${it.inc()}")}),
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