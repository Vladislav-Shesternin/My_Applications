package com.sca.rab.que.stgame.game.manager

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
        GAME(  AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Load
        LOA_LOAD    (TextureData("textures/loa_load.png")),
        LOA_LOADING (TextureData("textures/loa_loading.png")),
        LOA_MASK    (TextureData("textures/loa_mask.png")),
        LOALOAD_MAIN(TextureData("textures/LoaLoad_main.png")),
        // Igra
        LILIK          (TextureData("textures/lilik.png")),
        LOA_GAME       (TextureData("textures/loa_game.png")),
        LOA_LEVEL      (TextureData("textures/loa_level.png")),
        LOA_LOSE       (TextureData("textures/loa_lose.png")),
        LOA_MENU       (TextureData("textures/loa_menu.png")),
        LOA_RILUSE     (TextureData("textures/loa_riluse.png")),
        LOA_SETINGERROI(TextureData("textures/loa_setingerroi.png")),
        LOA_WIN        (TextureData("textures/loa_win.png")),
        TIMR_MASKR     (TextureData("textures/timr_maskr.png")),
        VOLUME_MASK    (TextureData("textures/volume_mask.png")),
        // Icons
        _1(TextureData("textures/1.png")),
        _2(TextureData("textures/2.png")),
        _3(TextureData("textures/3.png")),
        _4(TextureData("textures/4.png")),
        _5(TextureData("textures/5.png")),
        _6(TextureData("textures/6.png")),
        _7(TextureData("textures/7.png")),
        _8(TextureData("textures/8.png")),
        _9(TextureData("textures/9.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}