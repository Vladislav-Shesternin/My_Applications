package jogo.fortunetiger.tighrino.game.manager

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
        GAME (  AtlasData("atlas/game.atlas") ),
        ITEMS(  AtlasData("atlas/items.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Splash
        TLOAD (TextureData("textures/TLoad.png")),
        LOADER(TextureData("textures/loader.png")),
        MASK  (TextureData("textures/mask.png")),
        // Game
        B_FAILE   (TextureData("textures/b_faile.png")),
        B_MAIN    (TextureData("textures/b_main.png")),
        B_WIN     (TextureData("textures/b_win.png")),
        F_CHOOSE  (TextureData("textures/f_choose.png")),
        F_MENU    (TextureData("textures/f_menu.png")),
        F_RULES   (TextureData("textures/f_rules.png")),
        F_SETTINGS(TextureData("textures/f_settings.png")),
        TIGER1    (TextureData("textures/tiger1.png")),
        TIGER2    (TextureData("textures/tiger2.png")),
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