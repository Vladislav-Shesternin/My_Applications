package com.bettilt.mobile.pt.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound

class SoundManager(var assetManager: AssetManager) {

    var loadableSoundList = mutableListOf<SoundData>()

    fun load() {
        loadableSoundList.onEach { assetManager.load(it.path, Sound::class.java) }
    }

    fun init() {
        loadableSoundList.onEach { it.sound = assetManager[it.path, Sound::class.java] }
    }

    enum class EnumSound(val data: SoundData) {
        BOM(  SoundData("sound/bom.mp3")  ),
        TOUCH(SoundData("sound/touch.mp3")),
        CLUNK(SoundData("sound/clunk.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}