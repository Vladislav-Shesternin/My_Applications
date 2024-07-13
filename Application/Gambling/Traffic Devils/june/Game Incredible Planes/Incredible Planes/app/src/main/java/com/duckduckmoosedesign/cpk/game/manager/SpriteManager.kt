package com.duckduckmoosedesign.cpk.game.manager

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
        BERMUDKA(AtlasData("asset/bermudka.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        Firster(TextureData("texture/Firster.png")),

        MNL   (TextureData("texture/mnl.png")),
        MNS   (TextureData("texture/mns.png")),
        MSKLAJ(TextureData("texture/msklaj.png")),
        PNS   (TextureData("texture/pns.png")),
        SMS   (TextureData("texture/sms.png")),

        Ember   (TextureData("texture/Ember.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}