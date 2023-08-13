package com.veldan.lbjt.game.manager

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
        DEFAULT_1(MusicData("music/music_default 1.ogg")),
        DEFAULT_2(MusicData("music/music_default 2.ogg")),
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