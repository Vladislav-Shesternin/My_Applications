package com.fortunetiger.carnival.game.manager

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
        ALL   (  AtlasData("atlas/all.atlas")   ),
        LOADER(  AtlasData("atlas/loader.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Loader
        CARNAVAL(TextureData("textures/loader/CARNAVAL.png")),
        ts_msk  (TextureData("textures/loader/ts_msk.png")),
        // All
        FUu_BLA (TextureData("textures/all/FUu_BLA.png")),
        GAMNAVAL(TextureData("textures/all/GAMNAVAL.png")),
        Mentino (TextureData("textures/all/Mentino.png")),
        Music   (TextureData("textures/all/Music.png")),
        OGO_SKA (TextureData("textures/all/OGO_SKA.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}