package com.pharhaslo.slo7.game.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.pharhaslo.slo7.game.assets.util.SoundData

object SoundManager {

    var loadListSound = mutableListOf<IEnumSound>()

    fun load(assetManager: AssetManager) {
        loadListSound.onEach { assetManager.load(it.data.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListSound.onEach { it.data.sound = assetManager[it.data.path, Sound::class.java] }
    }



    enum class EnumSound(override val data: SoundData): IEnumSound {
        CLICK(SoundData("sound/click.ogg")),
        CHECK(SoundData("sound/check.ogg")),
        WIN(  SoundData("sound/win.ogg")  ),
    }



    interface IEnumSound {
        val data: SoundData
    }

}