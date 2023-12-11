package lycky.fortune.tiger.game.manager

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
        TOY(   AtlasData("atlas/toy.atlas")),
        SPLASH(AtlasData("atlas/splash.atlas")),
        GAME(  AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        TIGER_MASK(          TextureData("textures/tiger_mask.png")),
        VARIOUS_LUXURY_ITEMS(TextureData("textures/various_luxury_items.png")),
        FORTUNE_TIGER(       TextureData("textures/fortune_tiger.png")),
        FIRST_BACKGROUND(    TextureData("textures/first_background.png")),

        BAD(                 TextureData("textures/bad.png")),
        GREEAT(              TextureData("textures/greeat.png")),
        RUL(                 TextureData("textures/rul.png")),
        SET(                 TextureData("textures/set.png")),
        TIGER_MENU(          TextureData("textures/tiger_menu.png")),
        TIME_MASK(           TextureData("textures/time_mask.png")),
        TOY_PANEL(           TextureData("textures/toy_panel.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}