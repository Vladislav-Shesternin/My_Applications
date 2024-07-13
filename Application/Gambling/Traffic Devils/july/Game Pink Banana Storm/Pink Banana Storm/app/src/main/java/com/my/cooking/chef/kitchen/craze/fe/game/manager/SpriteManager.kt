package com.my.cooking.chef.kitchen.craze.fe.game.manager

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
        ALOLO(AtlasData("asset/alolo.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        b1               (TextureData("texture/b1.png")),
        b2               (TextureData("texture/b2.png")),
        b3               (TextureData("texture/b3.png")),

        BUTORS           (TextureData("texture/butors.png")),
        SETTINGESOPLUYTRE(TextureData("texture/settingesopluytre.png")),
        TEXESTEREWSWE    (TextureData("texture/texesterewswe.png")),

        diloger(TextureData("texture/diloger.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}