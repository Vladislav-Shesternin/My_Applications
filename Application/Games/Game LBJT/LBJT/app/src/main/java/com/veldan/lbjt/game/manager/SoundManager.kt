package com.veldan.lbjt.game.manager

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
        TOUCH_DOWN(SoundData("sound/touch.mp3")),

        ELECTRICITY(SoundData("sound/collision/electricity.mp3")),
        BORDER(     SoundData("sound/collision/border.mp3")     ),
        METAL(      SoundData("sound/collision/metal.mp3")      ),
        CLUNK(      SoundData("sound/collision/clunk.mp3")      ),
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