package com.cosmo.plinko.game.manager

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
        CLICK(SoundData("sound/click.mp3")),
        UDAR1(SoundData("sound/udar1.mp3")),
        UDAR2(SoundData("sound/udar2.mp3")),
        UDAR3(SoundData("sound/udar3.mp3")),
        UDAR4(SoundData("sound/udar4.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}