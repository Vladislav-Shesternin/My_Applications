package com.veldan.sportslots.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.veldan.sportslots.assets.util.MusicData

object MusicManager {

    var loadListMusic = mutableListOf<IEnumMusic>()

    fun load(assetManager: AssetManager) {
        loadListMusic.onEach { assetManager.load(it.musicData.path, Music::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListMusic.onEach { it.musicData.music = assetManager[it.musicData.path, Music::class.java] }
    }

    fun loadAll(assetManager: AssetManager) {
        loadListMusic = mutableListOf<IEnumMusic>(*EnumMusic.values(),)
        load(assetManager)
    }



    enum class EnumMusic(override val musicData: MusicData): IEnumMusic {
        MAIN(      MusicData("music/main.ogg")      ),
        BONUS_SPIN(MusicData("music/bonus_spin.ogg")),
    }



    interface IEnumMusic {
        val musicData: MusicData
    }

}