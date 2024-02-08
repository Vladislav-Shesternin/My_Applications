package com.playin.paganis.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.playin.paganis.game.utils.region

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
        P(      TextureAtlasData("atlas/p.atlas")      ),
        L(      TextureAtlasData("atlas/l.atlas")      ),
        N(      TextureAtlasData("atlas/n.atlas")      ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BAKKKICH( TextureData("textures/bakkkich.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BAKKKICH(EnumTexture.BAKKKICH.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACK(  EnumAtlas.GAME.data.atlas.findRegion("back")  ),
        NAMES( EnumAtlas.GAME.data.atlas.findRegion("names") ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        P_ICON( List(5) { EnumAtlas.P.data.atlas.findRegion("${it.inc()}") }),
        P_GAME( List(5) { EnumAtlas.P.data.atlas.findRegion("P${it.inc()}") }),
        L_ICON( List(5) { EnumAtlas.L.data.atlas.findRegion("${it.inc()}") }),
        L_GAME( List(5) { EnumAtlas.L.data.atlas.findRegion("L${it.inc()}") }),
        N_ICON( List(5) { EnumAtlas.N.data.atlas.findRegion("${it.inc()}") }),
        N_GAME( List(5) { EnumAtlas.N.data.atlas.findRegion("N${it.inc()}") }),
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