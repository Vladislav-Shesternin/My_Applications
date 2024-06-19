package com.bandagames.mpuzzle.g.game.manager

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
        ALLSTARS(AtlasData("asset/allstars.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        magi(TextureData("texture/magi.png")),

        pomaska   (TextureData("texture/pomaska.png")),
        saturetion(TextureData("texture/saturetion.png")),
        mesu      (TextureData("texture/mesu.png")),
        stall     (TextureData("texture/stall.png")),
        begi      (TextureData("texture/begi.png")),
        skoro_cia_hueta_zakonchitsa_i_bude_bogata_life      (TextureData("texture/skoro_cia_hueta_zakonchitsa_i_bude_bogata_life.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}