package qbl.bisriymyach.QuickBall.pitopilot

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class idi_naher(var assetManager: AssetManager) {

    var loadableAtlasList = mutableListOf<AtlasData>()
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
        Alas(AtlasData("alarmo/alas.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        FOREX(TextureData("rutex/forex.png")),
        POLILINE(TextureData("rutex/poliline.png")),
        ROPENDE(TextureData("rutex/ropende.png")),
        SETERO(TextureData("rutex/setero.png")),
        TABEX(TextureData("rutex/tabex.png")),
        WINLANDIA(TextureData("rutex/winlandia.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}