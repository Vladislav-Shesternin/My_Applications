package com.rochevasternin.physical.joints.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music

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
        song_1(MusicData("music/song 1.ogg")),
        song_2(MusicData("music/song 2.ogg")),
        song_3(MusicData("music/song 3.ogg")),
        song_4(MusicData("music/song 4.ogg")),
        song_5(MusicData("music/song 5.ogg")),
        song_6(MusicData("music/song 6.ogg")),
        song_7(MusicData("music/song 7.ogg")),
        song_8(MusicData("music/song 8.ogg")),
        song_9(MusicData("music/song 9.ogg")),
    }

    data class MusicData(
        val path: String,
    ) {
        lateinit var music: Music
    }

}