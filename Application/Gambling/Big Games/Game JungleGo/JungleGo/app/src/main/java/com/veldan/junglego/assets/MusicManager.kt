package com.veldan.junglego.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.veldan.junglego.assets.util.MusicData

object MusicManager {

    var loadListMusic = mutableListOf<IEnumMusic>()

    fun load(assetManager: AssetManager) {
        loadListMusic.onEach { assetManager.load(it.musicData.path, Music::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListMusic.onEach { it.musicData.music = assetManager[it.musicData.path, Music::class.java] }
    }



    enum class EnumMusic(override val musicData: MusicData): IEnumMusic {
        MAIN(      MusicData("music/main.ogg")        ),
        SPIN(      MusicData("music/spin.ogg")        ),
        MINI_GAME( MusicData("music/mini_game.ogg")   ),
        SUPER_GAME(MusicData("music/super_game.ogg")  ),
    }



    interface IEnumMusic {
        val musicData: MusicData
    }

}