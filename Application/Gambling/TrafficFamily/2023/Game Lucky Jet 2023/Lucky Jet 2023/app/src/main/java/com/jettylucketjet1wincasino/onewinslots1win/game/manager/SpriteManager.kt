package com.jettylucketjet1wincasino.onewinslots1win.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.region

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
        SPLASH(     TextureAtlasData("atlas/splash.atlas")     ),
        GAME(       TextureAtlasData("atlas/game.atlas")       ),
        MAN(        TextureAtlasData("atlas/man.atlas")        ),
        FIRE(       TextureAtlasData("atlas/fire.atlas")       ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND_1(TextureData("textures/background 1.png")),
        BACKGROUND_2(TextureData("textures/background 2.png")),
    }



    enum class SplashRegion(override val region: TextureRegion): IRegion {
        LOGO( EnumAtlas.SPLASH.data.atlas.findRegion("logo") ),

        BACKGROUND_1(EnumTexture.BACKGROUND_1.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        A(    EnumAtlas.GAME.data.atlas.findRegion("a")    ),
        B(    EnumAtlas.GAME.data.atlas.findRegion("b")    ),
        BTN(  EnumAtlas.GAME.data.atlas.findRegion("btn")  ),
        C(    EnumAtlas.GAME.data.atlas.findRegion("c")    ),
        MENU( EnumAtlas.GAME.data.atlas.findRegion("menu") ),
        NBTN( EnumAtlas.GAME.data.atlas.findRegion("nbtn") ),

        BACKGROUND_2(EnumTexture.BACKGROUND_2.data.texture.region),
    }


    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        MAN(     List(21) { EnumAtlas.MAN.data.atlas.findRegion("man (${it.inc()})") }),
        FIRE(    List(10) { EnumAtlas.FIRE.data.atlas.findRegion("fire (${it.inc()})") }),
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