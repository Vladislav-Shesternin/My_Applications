package plinko.gameballs.nine.game.manager

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
        B_LIGHTNING(        TextureData("textures/b_lightning.png")),
        B_LOADING(          TextureData("textures/b_loading.png")),
        BALL_AND_LOADING(   TextureData("textures/ball_and_loading.png")),
        LOADER(             TextureData("textures/loader.png")),

        B_GAME(      TextureData("textures/b_game.png")),
        BUTTONS(     TextureData("textures/buttons.png")),
        RULLES(      TextureData("textures/rulles.png")),
        SETTINGS(    TextureData("textures/settings.png")),
        WHITE(       TextureData("textures/white.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}