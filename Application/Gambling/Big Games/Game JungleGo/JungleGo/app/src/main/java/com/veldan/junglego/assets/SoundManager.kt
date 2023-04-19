package com.veldan.junglego.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.veldan.junglego.assets.util.SoundData

object SoundManager {

    var loadListSound = mutableListOf<IEnumSound>()

    fun load(assetManager: AssetManager) {
        loadListSound.onEach { assetManager.load(it.soundData.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListSound.onEach { it.soundData.sound = assetManager[it.soundData.path, Sound::class.java] }
    }



    enum class EnumSound(override val soundData: SoundData): IEnumSound {
        CLICK(SoundData("sound/click.ogg")),
        CHECK(SoundData("sound/check.ogg")),
        WIN(  SoundData("sound/win.ogg")  ),
        FAIL( SoundData("sound/fail.ogg") ),
    }



    interface IEnumSound {
        val soundData: SoundData
    }

}