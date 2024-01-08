package com.tigerfortune.jogo.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class SpriteManager(var assetManager: AssetManager) {

    var loadableAtlasList   = mutableListOf<AtlasData>()
    var loadableTextureList = mutableListOf<TextureData>()

    fun loadAtlas() {
        loadableAtlasList.onEach { assetManager.load(it.path, TextureAtlas::class.java) }
    }

    fun loadTexture() {
        loadableTextureList.onEach {
            assetManager.load(it.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initAtlas() {
        loadableAtlasList.onEach { it.atlas = assetManager[it.path, TextureAtlas::class.java] }
        loadableAtlasList.clear()
    }

    fun initTexture() {
        loadableTextureList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
        loadableTextureList.clear()
    }

    fun initAtlasAndTexture() {
        initAtlas()
        initTexture()
    }


    enum class EnumAtlas(val data: AtlasData) {
        SPLASH(  AtlasData("atlas/splash.atlas")),
        GAME  (  AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Splash
        TigerLoading(TextureData("textures/TigerLoading.png")),
        // Game
        FAIL(          TextureData("textures/fail.png")),
        PANEL_MENU(    TextureData("textures/panel_menu.png")),
        PANEL_RULES(   TextureData("textures/panel_rules.png")),
        PANEL_SETTINGS(TextureData("textures/panel_settings.png")),
        TIGERCHOOSE(   TextureData("textures/TigerChoose.png")),
        TWO_TIGER(     TextureData("textures/two_tiger.png")),
        WIN(           TextureData("textures/win.png")),
        // BG
        BG1(TextureData("textures/bg1.png")),
        BG2(TextureData("textures/bg2.png")),
        BG3(TextureData("textures/bg3.png")),
        BG4(TextureData("textures/bg4.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}