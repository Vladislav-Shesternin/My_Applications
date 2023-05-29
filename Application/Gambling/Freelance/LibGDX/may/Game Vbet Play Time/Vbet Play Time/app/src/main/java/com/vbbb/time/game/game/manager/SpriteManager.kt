package com.vbbb.time.game.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vbbb.time.game.game.utils.region

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
        UKRAINE( TextureData("textures/ukraine.png") ),

        LEVEL_1(TextureData("textures/level 1.png")),
        LEVEL_2(TextureData("textures/level 2.png")),
        LEVEL_3(TextureData("textures/level 3.png")),
        LEVEL_4(TextureData("textures/level 4.png")),

        LEA(TextureData("textures/lea.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        UKRAINE(EnumTexture.UKRAINE.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BALKA(    EnumAtlas.GAME.data.atlas.findRegion("balka")    ),
        BALL(     EnumAtlas.GAME.data.atlas.findRegion("ball")     ),
        BOTTOM(   EnumAtlas.GAME.data.atlas.findRegion("bottom")   ),
        MENU_D(   EnumAtlas.GAME.data.atlas.findRegion("menu_d")   ),
        MENU_P(   EnumAtlas.GAME.data.atlas.findRegion("menu_p")   ),
        MINI_NOT( EnumAtlas.GAME.data.atlas.findRegion("mini_not") ),
        MINI_YES( EnumAtlas.GAME.data.atlas.findRegion("mini_yes") ),
        SOUND(    EnumAtlas.GAME.data.atlas.findRegion("sound")    ),
        SOUND_NO( EnumAtlas.GAME.data.atlas.findRegion("sound_no") ),
        TARGET(   EnumAtlas.GAME.data.atlas.findRegion("target")   ),
        TOP(      EnumAtlas.GAME.data.atlas.findRegion("top")      ),

        LEVEL_1(EnumTexture.LEVEL_1.data.texture.region),
        LEVEL_2(EnumTexture.LEVEL_2.data.texture.region),
        LEVEL_3(EnumTexture.LEVEL_3.data.texture.region),
        LEVEL_4(EnumTexture.LEVEL_4.data.texture.region),

        LEA(EnumTexture.LEA.data.texture.region),
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