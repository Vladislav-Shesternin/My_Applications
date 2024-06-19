package com.duckduckmoosedesign.cpkid.game.manager

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

    fun initTeture() {
        loadableTexturesList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
        loadableTexturesList.clear()
    }


    enum class EnumAtlas(val data: AtlasData) {
        ASSETS(AtlasData("asset/assets.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        BEDROOM(TextureData("texture/bedroom.png")),

        kilotonna   (TextureData("texture/kilotonna.png")),
        left        (TextureData("texture/left.png")),
        miskappppppp(TextureData("texture/miskappppppp.png")),
        paramon     (TextureData("texture/paramon.png")),
        right       (TextureData("texture/right.png")),
        rudles      (TextureData("texture/rudles.png")),
        soundersia  (TextureData("texture/soundersia.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}