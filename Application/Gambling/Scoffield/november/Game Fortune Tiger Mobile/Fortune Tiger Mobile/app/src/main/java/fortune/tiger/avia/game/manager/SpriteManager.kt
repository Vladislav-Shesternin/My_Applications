package fortune.tiger.avia.game.manager

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
        SPLASHIMG(TextureData("textures/splashimg.png")),

        LEVELSIMG(      TextureData("textures/levelsimg.png")),
        VICTORY(        TextureData("textures/victory.png")),
        STEEPLY(        TextureData("textures/steeply.png")),
        GOODJOB(        TextureData("textures/goodjob.png")),
        ULERUSIMG(      TextureData("textures/ulerusimg.png")),
        LOSE(           TextureData("textures/lose.png")),
        TESSINGSIMG(    TextureData("textures/tessingsimg.png")),
        IGRABELNAGRAIMG(TextureData("textures/igrabelnagraimg.png")),
        PG_MASKA(       TextureData("textures/pg_maska.png")),
        MENUIMG(        TextureData("textures/menuimg.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}