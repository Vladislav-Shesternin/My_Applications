package com.veldan.sportslots.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.veldan.sportslots.assets.util.SoundData

object SoundManager {

    var loadListSound = mutableListOf<IEnumSound>()

    fun load(assetManager: AssetManager) {
        loadListSound.onEach { assetManager.load(it.soundData.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListSound.onEach { it.soundData.sound = assetManager[it.soundData.path, Sound::class.java] }
    }

    fun loadAll(assetManager: AssetManager) {
        loadListSound = mutableListOf<IEnumSound>(*EnumSound.values())
        load(assetManager)
    }



    enum class EnumSound(override val soundData: SoundData): IEnumSound {
        CLICK(     SoundData("sound/click.ogg")     ),
        SHOW_BONUS(SoundData("sound/show_bonus.ogg")),
        WIN_BONUS( SoundData("sound/win_bonus.ogg") ),
        FAIL_BONUS(SoundData("sound/fail_bonus.ogg")),
    }



    interface IEnumSound {
        val soundData: SoundData
    }

}