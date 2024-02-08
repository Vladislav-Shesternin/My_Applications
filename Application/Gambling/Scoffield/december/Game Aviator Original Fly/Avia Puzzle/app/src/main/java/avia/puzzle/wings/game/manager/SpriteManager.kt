package avia.puzzle.wings.game.manager

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
        GAME(   AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        AviatorLoading(     TextureData("textures/AviatorLoading.png")),

        avia_btns(          TextureData("textures/avia_btns.png")),
        AviatorResultFalse( TextureData("textures/AviatorResultFalse.png")),
        AviatorResultTrue(  TextureData("textures/AviatorResultTrue.png")),
        menu(               TextureData("textures/menu.png")),
        rules(              TextureData("textures/rules.png")),
        settings(           TextureData("textures/settings.png")),

        puzzle1(TextureData("textures/puzzle1.png")),
        puzzle2(TextureData("textures/puzzle2.png")),
        puzzle3(TextureData("textures/puzzle3.png")),
        puzzle4(TextureData("textures/puzzle4.png")),
        puzzle5(TextureData("textures/puzzle5.png")),
        puzzle6(TextureData("textures/puzzle6.png")),
        puzzle7(TextureData("textures/puzzle7.png")),
        puzzle8(TextureData("textures/puzzle8.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}