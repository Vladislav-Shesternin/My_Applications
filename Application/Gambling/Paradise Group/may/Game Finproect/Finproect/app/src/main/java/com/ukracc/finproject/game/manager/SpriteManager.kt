package com.ukracc.finproject.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.ukracc.finproject.game.utils.region

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
        GAME(    TextureAtlasData("atlas/game.atlas")    ),
        ITEMS(   TextureAtlasData("atlas/items.atlas")   ),
        SETTINGS(TextureAtlasData("atlas/settings.atlas")),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND( TextureData("textures/background.png") ),
        ICON(       TextureData("textures/icon.png"      ) ),
        BTNS(       TextureData("textures/btns.png"      ) ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
        ICON(      EnumTexture.ICON.data.texture.region      ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BTN_DEF(       EnumAtlas.GAME.data.atlas.findRegion("btn_def")       ),
        BTN_PRESS(     EnumAtlas.GAME.data.atlas.findRegion("btn_press")     ),
        BTN_DIS(       EnumAtlas.GAME.data.atlas.findRegion("btn_dis")       ),
        CB_CHECK(      EnumAtlas.GAME.data.atlas.findRegion("cb_check")      ),
        CB_DEF(        EnumAtlas.GAME.data.atlas.findRegion("cb_def")        ),
        PRIVACY_PANEL( EnumAtlas.GAME.data.atlas.findRegion("privacy_panel") ),
        SEPARATOR(     EnumAtlas.GAME.data.atlas.findRegion("separator")     ),
        BTN(           EnumAtlas.GAME.data.atlas.findRegion("btn")           ),

        BTNS(      EnumTexture.BTNS.data.texture.region      ),
    }

    enum class SettingsRegion(override val region: TextureRegion): IRegion {
        BACK(     EnumAtlas.SETTINGS.data.atlas.findRegion("back")     ),
        CLICK(    EnumAtlas.SETTINGS.data.atlas.findRegion("click")    ),
        FRAME(    EnumAtlas.SETTINGS.data.atlas.findRegion("frame")    ),
        MASK(     EnumAtlas.SETTINGS.data.atlas.findRegion("mask")     ),
        MS(       EnumAtlas.SETTINGS.data.atlas.findRegion("ms")       ),
        PROGRESS( EnumAtlas.SETTINGS.data.atlas.findRegion("progress") ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS( List(16) { EnumAtlas.ITEMS.data.atlas.findRegion("item ${it.inc()}") }),
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