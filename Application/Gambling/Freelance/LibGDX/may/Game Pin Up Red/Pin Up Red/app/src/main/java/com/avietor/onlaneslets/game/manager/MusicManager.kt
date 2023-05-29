package com.avietor.onlaneslets.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music

object MusicManager {

    var loadableListMusic = mutableListOf<IEnumMusic>()

    fun load(assetManager: AssetManager) {
        loadableListMusic.onEach { assetManager.load(it.data.path, Music::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableListMusic.onEach { it.data.music = assetManager[it.data.path, Music::class.java] }
    }



    enum class EnumMusic(override val data: MusicData): IEnumMusic {
        MAIN(      MusicData("music/Happy-and-Fun-Pop-Background.ogg")      ),
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