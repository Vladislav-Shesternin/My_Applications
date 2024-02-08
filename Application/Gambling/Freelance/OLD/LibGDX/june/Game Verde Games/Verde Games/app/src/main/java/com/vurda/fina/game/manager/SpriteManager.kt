package com.vurda.fina.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vurda.fina.game.utils.region

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
        BARAGRANDOM( TextureData("textures/baragrandom.png") ),
        DEVKA(       TextureData("textures/devka.png")       ),
        LEV_1(       TextureData("textures/lev 1.png")       ),
        LEV_2(       TextureData("textures/lev 2.png")       ),
        LEV_3(       TextureData("textures/lev 3.png")       ),
        LEV_4(       TextureData("textures/lev 4.png")       ),
        LEV_5(       TextureData("textures/lev 5.png")       ),
        SOVA(        TextureData("textures/sova.png")        ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BARAGRANDOM( EnumTexture.BARAGRANDOM.data.texture.region ),
        SOVA(        EnumTexture.SOVA.data.texture.region        ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BALL(        EnumAtlas.GAME.data.atlas.findRegion("ball")        ),
        BALL_NOT(    EnumAtlas.GAME.data.atlas.findRegion("ball_not")    ),
        CUB_NE(      EnumAtlas.GAME.data.atlas.findRegion("cub_ne")      ),
        CUBOK_E(     EnumAtlas.GAME.data.atlas.findRegion("cubok_e")     ),
        MUZON(       EnumAtlas.GAME.data.atlas.findRegion("muzon")       ),
        MUSISISID(   EnumAtlas.GAME.data.atlas.findRegion("musisisid")   ),
        PLATFORMA(   EnumAtlas.GAME.data.atlas.findRegion("platforma")   ),
        SIDE(        EnumAtlas.GAME.data.atlas.findRegion("side")        ),
        YOU_ARE_WIN( EnumAtlas.GAME.data.atlas.findRegion("you_are_win") ),

        DEVKA(EnumTexture.DEVKA.data.texture.region),
        LEV_1(EnumTexture.LEV_1.data.texture.region),
        LEV_2(EnumTexture.LEV_2.data.texture.region),
        LEV_3(EnumTexture.LEV_3.data.texture.region),
        LEV_4(EnumTexture.LEV_4.data.texture.region),
        LEV_5(EnumTexture.LEV_5.data.texture.region),
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