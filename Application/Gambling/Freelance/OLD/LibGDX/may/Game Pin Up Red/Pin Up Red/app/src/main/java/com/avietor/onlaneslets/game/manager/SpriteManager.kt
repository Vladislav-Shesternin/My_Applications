package com.avietor.onlaneslets.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.avietor.onlaneslets.game.utils.region

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
        BARAKUDA( TextureData("textures/barakuda.png") ),

        LEVEL_1(TextureData("textures/level 1.png")),
        LEVEL_2(TextureData("textures/level 2.png")),
        LEVEL_3(TextureData("textures/level 3.png")),
        LEVEL_4(TextureData("textures/level 4.png")),

        LEA(TextureData("textures/lea.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BARAKUDA(EnumTexture.BARAKUDA.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BIGI(        EnumAtlas.GAME.data.atlas.findRegion("bigi")        ),
        DEF(         EnumAtlas.GAME.data.atlas.findRegion("def")         ),
        DIS(         EnumAtlas.GAME.data.atlas.findRegion("dis")         ),
        ENEMY_ROTTE( EnumAtlas.GAME.data.atlas.findRegion("enemy_rotte") ),
        ENEMY_TRY(   EnumAtlas.GAME.data.atlas.findRegion("enemy_try")   ),
        FAIL(        EnumAtlas.GAME.data.atlas.findRegion("fail")        ),
        LEF(         EnumAtlas.GAME.data.atlas.findRegion("lef")         ),
        MINI(        EnumAtlas.GAME.data.atlas.findRegion("mini")        ),
        MIS(         EnumAtlas.GAME.data.atlas.findRegion("mis")         ),
        MUNI(        EnumAtlas.GAME.data.atlas.findRegion("muni")        ),
        MUS(         EnumAtlas.GAME.data.atlas.findRegion("mus")         ),
        RED(         EnumAtlas.GAME.data.atlas.findRegion("red")         ),
        STAR(        EnumAtlas.GAME.data.atlas.findRegion("star")        ),
        TAKE_STAR(   EnumAtlas.GAME.data.atlas.findRegion("take_star")   ),
        TOP(         EnumAtlas.GAME.data.atlas.findRegion("top")         ),
        WIN(         EnumAtlas.GAME.data.atlas.findRegion("win")         ),
        KNOPKA(      EnumAtlas.GAME.data.atlas.findRegion("knopka")      ),
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