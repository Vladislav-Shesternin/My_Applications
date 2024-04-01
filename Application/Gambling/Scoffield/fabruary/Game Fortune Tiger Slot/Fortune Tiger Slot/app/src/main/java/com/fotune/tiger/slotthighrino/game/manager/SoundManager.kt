package com.fotune.tiger.slotthighrino.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound

class SoundManager(var assetManager: AssetManager) {

    var loadableSoundList = mutableListOf<SoundData>()

    fun load() {
        loadableSoundList.onEach { assetManager.load(it.path, Sound::class.java) }
    }

    fun init() {
        loadableSoundList.onEach { it.sound = assetManager[it.path, Sound::class.java] }
        loadableSoundList.clear()
    }

    enum class EnumSound(val data: SoundData) {
        end (SoundData("sound/end.webm")),
        roll(SoundData("sound/roll.webm")),
        spin(SoundData("sound/spin.webm")),
        win (SoundData("sound/win.webm")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}