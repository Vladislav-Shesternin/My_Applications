package finalizer.masturbaizer.lotos.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture

class SpriteManager(var assetManager: AssetManager) {

    var loadableTextureList = mutableListOf<TextureData>()


    fun loadTexture() {
        loadableTextureList.onEach {
            assetManager.load(it.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initTexture() {
        loadableTextureList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
    }


    enum class EnumTexture(val data: TextureData) {
        barabanovka(TextureData("textures/barabanovka.png")),
        f_bacgro(TextureData("textures/f_bacgro.png")),
        rezult_backig(TextureData("textures/rezult_backig.png")),
        www(TextureData("textures/www.png")),
        qqq(TextureData("textures/qqq.png")),
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}