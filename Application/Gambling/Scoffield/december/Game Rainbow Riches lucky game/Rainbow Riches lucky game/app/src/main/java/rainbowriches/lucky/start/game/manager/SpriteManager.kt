package rainbowriches.lucky.start.game.manager

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
        ASK_MY_NAME(TextureData("textures/ask_my_name.png")),
        BLACKWOOD(  TextureData("textures/blackwood.png")),

        PANEL_GREEN(    TextureData("textures/panel_green.png")),
        PANEL_PLAY_GAME(TextureData("textures/panel_play_game.png")),
        PLAY_DEF(       TextureData("textures/play_def.png")),
        PLAY_DIS(       TextureData("textures/play_dis.png")),
        PLAYGROUND(     TextureData("textures/playground.png")),
        SEASON(         TextureData("textures/season.png")),
        WIN_LOSE_BLOOR( TextureData("textures/win_lose_bloor.png")),

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