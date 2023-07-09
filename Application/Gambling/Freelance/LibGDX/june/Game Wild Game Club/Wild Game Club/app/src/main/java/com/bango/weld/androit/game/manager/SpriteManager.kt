package com.bango.weld.androit.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bango.weld.androit.game.utils.region

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
        GAME(   TextureAtlasData("atlas/game.atlas")     ),
        SMILE(  TextureAtlasData("atlas/smails.atlas")   ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BADABPUMBA( TextureData("textures/badabpumba.png") ),
    }


    enum class GameRegion(override val region: TextureRegion): IRegion {
        CATCH(      EnumAtlas.GAME.data.atlas.findRegion("catch")      ),
        EXITPLAY(   EnumAtlas.GAME.data.atlas.findRegion("exitplay")   ),
        LEFT(       EnumAtlas.GAME.data.atlas.findRegion("left")       ),
        MUSIC_PLAY( EnumAtlas.GAME.data.atlas.findRegion("music_play") ),
        MUSIC_STOP( EnumAtlas.GAME.data.atlas.findRegion("music_stop") ),
        RIGHT(      EnumAtlas.GAME.data.atlas.findRegion("right")      ),

        BADABPUMBA(EnumTexture.BADABPUMBA.data.texture.region),
    }


    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        SMILE( List(30) { EnumAtlas.SMILE.data.atlas.findRegion("${it.inc()}") }),
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