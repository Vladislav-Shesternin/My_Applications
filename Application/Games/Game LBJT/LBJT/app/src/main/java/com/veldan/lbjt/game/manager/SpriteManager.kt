package com.veldan.lbjt.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.lbjt.game.utils.region

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
        YAN_BACKGROUND(TextureData("textures/yan_background.png")),
        YIN_BACKGROUND(TextureData("textures/yin_background.png")),
    }



    enum class GameRegion(val region: TextureRegion) {
        CIRCLE_BLUE(       EnumAtlas.GAME.data.atlas.findRegion("circle_blue")       ),
        HAND_HELLO(        EnumAtlas.GAME.data.atlas.findRegion("hand_hello")        ),
        HAND_HINT(         EnumAtlas.GAME.data.atlas.findRegion("hand_hint")         ),
        JOINT    (         EnumAtlas.GAME.data.atlas.findRegion("joint")             ),
        REGULAR_BTN_DEF(   EnumAtlas.GAME.data.atlas.findRegion("regular_btn_def")   ),
        REGULAR_BTN_PRESS( EnumAtlas.GAME.data.atlas.findRegion("regular_btn_press") ),
        USER(              EnumAtlas.GAME.data.atlas.findRegion("user")              ),
        YAN(               EnumAtlas.GAME.data.atlas.findRegion("yan")               ),
        YIN(               EnumAtlas.GAME.data.atlas.findRegion("yin")               ),
        YIN_YAN_LIGHT(     EnumAtlas.GAME.data.atlas.findRegion("yin_yan_light")     ),

        YAN_BACKGROUND(EnumTexture.YAN_BACKGROUND.data.texture.region),
        YIN_BACKGROUND(EnumTexture.YIN_BACKGROUND.data.texture.region),
    }

//    enum class Game9Path(val ninePatch: NinePatch) {
//        JOINT(EnumAtlas.GAME.data.atlas.createPatch("joint")),
//    }

    //    enum class ListRegion(val regionList: List<TextureRegion>) {
    //        LOADER( List(101) { EnumAtlas.LOADER.data.atlas.findRegion("$it") }),
    //    }



    interface IAtlas {
        val data: TextureAtlasData
    }

    interface ITexture {
        val data: TextureData
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