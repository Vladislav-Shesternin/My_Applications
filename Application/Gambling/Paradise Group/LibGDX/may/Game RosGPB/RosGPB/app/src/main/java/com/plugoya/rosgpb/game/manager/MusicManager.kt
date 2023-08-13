package com.plugoya.rosgpb.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music

object MusicManager {

    var loadableMusicList = mutableListOf<IEnumMusic>()

    fun load(assetManager: AssetManager) {
        loadableMusicList.onEach { assetManager.load(it.data.path, Music::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableMusicList.onEach { it.data.music = assetManager[it.data.path, Music::class.java] }
    }



    enum class EnumMusic(override val data: MusicData): IEnumMusic {
        MAIN(      MusicData("music/music.ogg")      ),
//        MINI_GAME( MusicData("music/mini_game.ogg") ),
//        SUPER_GAME(MusicData("music/super_game.ogg")),
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