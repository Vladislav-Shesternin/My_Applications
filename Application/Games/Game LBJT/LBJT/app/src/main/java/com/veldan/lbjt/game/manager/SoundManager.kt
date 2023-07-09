package com.veldan.lbjt.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound

object SoundManager {

    var loadableSoundList = mutableListOf<ISound>()

    fun load(assetManager: AssetManager) {
        loadableSoundList.onEach { assetManager.load(it.data.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableSoundList.onEach { it.data.sound = assetManager[it.data.path, Sound::class.java] }
    }



    enum class EnumSound(override val data: SoundData): ISound {
//        CLICK(SoundData("sound/click.ogg")),
//        STAR( SoundData("sound/star.ogg") ),
//        STONE(SoundData("sound/stone.ogg")),
    }



    interface ISound {
        val data: SoundData
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}