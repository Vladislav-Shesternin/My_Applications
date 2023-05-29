package com.hello.piramidka.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hello.piramidka.game.utils.region

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
        GAME(  TextureAtlasData("atlas/game.atlas")  ),
        DARK(  TextureAtlasData("atlas/dark.atlas")  ),
        LIGHT( TextureAtlasData("atlas/light.atlas") ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
//        BACKGROUND( TextureData("textures/backgroundick.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
//        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        DEFFFF(     EnumAtlas.GAME.data.atlas.findRegion("deffff")     ),
        PR_TR_TEXT( EnumAtlas.GAME.data.atlas.findRegion("pr_tr_text") ),
        PRESSS(     EnumAtlas.GAME.data.atlas.findRegion("presss")     ),
        THEME(      EnumAtlas.GAME.data.atlas.findRegion("theme")      ),
        P_CB_D(     EnumAtlas.GAME.data.atlas.findRegion("p_cb_d")     ),
        P_CB_P(     EnumAtlas.GAME.data.atlas.findRegion("p_cb_p")     ),
        WHITER(     EnumAtlas.GAME.data.atlas.findRegion("whiter")     ),
    }

    enum class DarkRegion(override val region: TextureRegion): IRegion {
        BACK_D(     EnumAtlas.DARK.data.atlas.findRegion("back_d")     ),
        CB_DEF_D(   EnumAtlas.DARK.data.atlas.findRegion("cb_def_d")   ),
        CB_PRESS_D( EnumAtlas.DARK.data.atlas.findRegion("cb_press_d") ),
        CONVER_D(   EnumAtlas.DARK.data.atlas.findRegion("conver_d")   ),
        MEN_D(      EnumAtlas.DARK.data.atlas.findRegion("men_d")      ),
        FRAME_D(    EnumAtlas.DARK.data.atlas.findRegion("frame_d")    ),
    }

    enum class LightRegion(override val region: TextureRegion): IRegion {
        BACK_L(     EnumAtlas.LIGHT.data.atlas.findRegion("back_l")     ),
        CB_DEF_L(   EnumAtlas.LIGHT.data.atlas.findRegion("cb_def_l")   ),
        CB_PRESS_L( EnumAtlas.LIGHT.data.atlas.findRegion("cb_press_l") ),
        CONVER_L(   EnumAtlas.LIGHT.data.atlas.findRegion("conver_l")   ),
        MEN_L(      EnumAtlas.LIGHT.data.atlas.findRegion("men_l")      ),
        FRAME_L(    EnumAtlas.LIGHT.data.atlas.findRegion("frame_l")    ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
//        ITEMS( List(16) { EnumAtlas.ITEMS.data.atlas.findRegion("$it") }),
//        NEWS( List(20) { EnumAtlas.ITEMS.data.atlas.findRegion("news ${it.inc()}") }),
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