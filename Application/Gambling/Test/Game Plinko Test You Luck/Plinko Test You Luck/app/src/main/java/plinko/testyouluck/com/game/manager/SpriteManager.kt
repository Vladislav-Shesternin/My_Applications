package plinko.testyouluck.com.game.manager

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
        SPLASH(  AtlasData("atlas/splash.atlas")),
        GAME  (  AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        // Splash
        PLINKO_BACKGROUND(  TextureData("textures/plinko_background.png")),
        COINS_AND_BOMB(     TextureData("textures/coins_and_bomb.png")),
        LOADER_PLINKO_BALL( TextureData("textures/loader_plinko_ball.png")),
        LOADER_MASK(        TextureData("textures/loader_mask.png")),
        // Game
        MENU_BAR(           TextureData("textures/menu_bar.png")),
        PLINKO_PURPLE(      TextureData("textures/plinko_purple.png")),
        PLINKO_RED(         TextureData("textures/plinko_red.png")),
        PLINKO_RESULT(      TextureData("textures/plinko_result.png")),
        RULES_BAR(          TextureData("textures/rules_bar.png")),
        SETTINGS_BAR(       TextureData("textures/settings_bar.png")),
        SHOP_BAR(           TextureData("textures/shop_bar.png")),
        TOP_BOTTOM_BALLS(   TextureData("textures/top_bottom_balls.png")),
        TEST(               TextureData("textures/test.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}