package mobile.jogodobicho.lucky.game.manager

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
        GAME   (AtlasData("atlas/game.atlas")),
        LOADING(AtlasData("atlas/loading.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        PROGIGRESS_MASKARADJA (TextureData("textures/progigress_maskaradja.png")),
        SUPER_PUPER_BACKGROUND(TextureData("textures/super_puper_background.png")),

        BULL_LEVEL            (TextureData("textures/bull_level.png")),
        BULL_MENU             (TextureData("textures/bull_menu.png")),
        LEVELFAILED           (TextureData("textures/LevelFailed.png")),
        LEVELPASSED           (TextureData("textures/LevelPassed.png")),
        MENU_BABOCHKE         (TextureData("textures/menu_babochke.png")),
        OPTIONALE             (TextureData("textures/optionale.png")),
        TRIM_MASK             (TextureData("textures/trim_mask.png")),
        VOLUMERKA_MASAKA      (TextureData("textures/volumerka_masaka.png")),
        PALANKA               (TextureData("textures/palanka.png")),

        puzzle_easy_1  (TextureData("textures/puzzle easy 1.png")),
        puzzle_easy_2  (TextureData("textures/puzzle easy 2.png")),
        puzzle_easy_3  (TextureData("textures/puzzle easy 3.png")),
        puzzle_medium_1(TextureData("textures/puzzle medium 1.png")),
        puzzle_medium_2(TextureData("textures/puzzle medium 2.png")),
        puzzle_medium_3(TextureData("textures/puzzle medium 3.png")),
        puzzle_hard_1  (TextureData("textures/puzzle hard 1.png")),
        puzzle_hard_2  (TextureData("textures/puzzle hard 2.png")),
        puzzle_hard_3  (TextureData("textures/puzzle hard 3.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}