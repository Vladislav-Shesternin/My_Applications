package com.bitmango.go.game.manager

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
        leonardo(AtlasData("assets/leonardo.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        g1(TextureData("textures/lod/g1.png")),
        g2(TextureData("textures/lod/g2.png")),
        g3(TextureData("textures/lod/g3.png")),

        ewr   (TextureData("textures/ewr.png")   ),
        qsde  (TextureData("textures/qsde.png")  ),
        utytrg(TextureData("textures/utytrg.png")),

        Jorg(TextureData("textures/Jorg.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}