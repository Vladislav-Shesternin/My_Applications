package com.jumping.cubuletus.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

object SpriteManager {

    var loadableAtlasList   = mutableListOf<IAtlas>()
    //var loadableTextureList = mutableListOf<ITexture>()



    fun loadAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
    }

//    fun loadTexture(assetManager: AssetManager) {
//        loadableTextureList.onEach {
//            assetManager.load(it.data.path, Texture::class.java, TextureLoader.TextureParameter().apply {
//                minFilter = Texture.TextureFilter.Linear
//                magFilter = Texture.TextureFilter.Linear
//                genMipMaps = true
//            })
//        }
//    }

    fun initAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
    }

//    fun initTexture(assetManager: AssetManager) {
//        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
//    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("atlas/1.atlas")),
    }

//    enum class EnumTexture(override val data: TextureData): ITexture {
//        BACKGROUND(TextureData("textures/background.png")),
//    }

    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background"))
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BATUT(       EnumAtlas._1.data.atlas.findRegion("batut")       ),
        COIN(        EnumAtlas._1.data.atlas.findRegion("coin")        ),
        PANEL(       EnumAtlas._1.data.atlas.findRegion("panel")       ),
        MINI_COIN(   EnumAtlas._1.data.atlas.findRegion("mini_coin")   ),
        COUB(        EnumAtlas._1.data.atlas.findRegion("coub")        ),
        ROTATE_BTN(  EnumAtlas._1.data.atlas.findRegion("rotate_btn")  ),
        ROTATE_LINE( EnumAtlas._1.data.atlas.findRegion("rotate_line") ),
        UP(          EnumAtlas._1.data.atlas.findRegion("up")          ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        COLORS(List(4) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") }),
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