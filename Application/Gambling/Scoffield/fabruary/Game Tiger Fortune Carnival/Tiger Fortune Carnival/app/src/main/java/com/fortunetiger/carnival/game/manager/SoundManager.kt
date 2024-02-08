package com.fortunetiger.carnival.game.manager

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
        bass_drop(SoundData("sound/bass-drop.mp3")),
        click    (SoundData("sound/click.mp3")),
        fail     (SoundData("sound/fail.mp3")),
        touch    (SoundData("sound/touch.mp3")),
        win      (SoundData("sound/win.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}