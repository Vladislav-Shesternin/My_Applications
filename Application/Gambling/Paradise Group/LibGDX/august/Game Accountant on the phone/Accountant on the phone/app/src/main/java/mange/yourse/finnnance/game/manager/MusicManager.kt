package mange.yourse.finnnance.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music

class MusicManager(var assetManager: AssetManager) {

    var loadableMusicList = mutableListOf<IEnumMusic>()

    fun load() {
        loadableMusicList.onEach { assetManager.load(it.data.path, Music::class.java) }
    }

    fun init() {
        loadableMusicList.onEach { it.data.music = assetManager[it.data.path, Music::class.java] }
    }

    enum class EnumMusic(override val data: MusicData): IEnumMusic {
        DEFAULT(MusicData("music/patimeyker-hey-patimeyker.mp3")),
    }

    interface IEnumMusic {
        val data: MusicData
    }

    data class MusicData(
        val path: String,
    ) {
        lateinit var music: Music
    }

}