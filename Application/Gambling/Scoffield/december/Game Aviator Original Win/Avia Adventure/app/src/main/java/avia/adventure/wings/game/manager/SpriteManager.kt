package avia.adventure.wings.game.manager

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
        LOADING(AtlasData("atlas/loading.atlas")),
        GAME   (AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Splash
        OriginalLoading(TextureData("textures/OriginalLoading.png")),
        // Game
        MainBackground (TextureData("textures/MainBackground.png")),
        Menu           (TextureData("textures/Menu.png")),
        OriginalGame   (TextureData("textures/OriginalGame.png")),
        OriginalLose   (TextureData("textures/OriginalLose.png")),
        OriginalWin    (TextureData("textures/OriginalWin.png")),
        Rules          (TextureData("textures/Rules.png")),
        Settings       (TextureData("textures/Settings.png")),
        shop           (TextureData("textures/shop.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}