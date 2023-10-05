package com.lohina.titantreasuretrove.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound

class SoundManager(var assetManager: AssetManager) {

    var loadableSoundList = mutableListOf<ISound>()

    fun load() {
        loadableSoundList.onEach { assetManager.load(it.data.path, Sound::class.java) }
    }

    fun init() {
        loadableSoundList.onEach { it.data.sound = assetManager[it.data.path, Sound::class.java] }
    }

    enum class EnumSound(override val data: SoundData): ISound {
        AY(SoundData("sound/ay.mp3")),
        ITEM(  SoundData("sound/item.mp3")  ),
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