package com.tutotoons.app.kpopsiescuteunicornpet.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class SpriteManager(var assetManager: AssetManager) {

    var loadableAtlasList   = mutableListOf<AtlasData>()
    var loadableTexturesList   = mutableListOf<TextureData>()

    fun loadAtlas() {
        loadableAtlasList.onEach { assetManager.load(it.path, TextureAtlas::class.java) }
    }

    fun initAtlas() {
        loadableAtlasList.onEach { it.atlas = assetManager[it.path, TextureAtlas::class.java] }
        loadableAtlasList.clear()
    }

    // Texture
    fun loadTexture() {
        loadableTexturesList.onEach { assetManager.load(it.path, Texture::class.java) }
    }

    fun initTexture() {
        loadableTexturesList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
        loadableTexturesList.clear()
    }

    fun initAtlasAndTexture() {
        initAtlas()
        initTexture()
    }


    enum class EnumAtlas(val data: AtlasData) {
        all(AtlasData("atlas/all.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        background_purple(TextureData("textures/loader/background_purple.png")),

        gamedofit      (TextureData("textures/all/gamedofit.png")),
        music_adn_sound(TextureData("textures/all/music_adn_sound.png")),
        p_masaka       (TextureData("textures/all/p_masaka.png")),
        raeleus        (TextureData("textures/all/raeleus.png")),

        b_back (TextureData("textures/all/b_back.png")),
        b_music(TextureData("textures/all/b_music.png")),
        b_sound(TextureData("textures/all/b_sound.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}