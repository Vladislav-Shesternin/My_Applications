package plinko.games.mega.game.manager

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
        SPLASH(AtlasData("atlas/splash.atlas")),
        GAME(  AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        BACKGROUND( (TextureData("textures/background.png"))),
        MASK(       (TextureData("textures/mask.png"))),

        COOL(       (TextureData("textures/cool.png"))),
        GOOD(       (TextureData("textures/good.png"))),
        LOST(       (TextureData("textures/lost.png"))),
        MENU(       (TextureData("textures/menu.png"))),
        NOTBAD(     (TextureData("textures/notbad.png"))),
        RULES(      (TextureData("textures/rules.png"))),
        SETTINGS(   (TextureData("textures/settings.png"))),
        BACGROND_LEVEL(   (TextureData("textures/bacgrond_level.png"))),
        RESULT_GOOD(   (TextureData("textures/result_good.png"))),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}