package plinko.testyouluck.com.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Array

class MusicManager(var assetManager: AssetManager) {

    var loadableMusicList = mutableListOf<MusicData>()

    fun load() {
        loadableMusicList.onEach { assetManager.load(it.path, Music::class.java) }
    }

    fun init() {
        loadableMusicList.onEach { it.music = assetManager[it.path, Music::class.java] }
        loadableMusicList.clear()
    }

    enum class EnumMusic(val data: MusicData) {
        MUSIC(MusicData("music/8-bit-arcade-music.mp3")),
    }

    data class MusicData(
        val path: String,
    ) {
        lateinit var music: Music
    }

}