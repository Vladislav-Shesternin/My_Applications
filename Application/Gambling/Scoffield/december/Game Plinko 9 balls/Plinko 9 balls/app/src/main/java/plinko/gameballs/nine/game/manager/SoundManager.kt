package plinko.gameballs.nine.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Array

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
        soundsuddenly(SoundData("sound/suddenly.mp3")),
        soundpunch1(SoundData("sound/punch-1.mp3")),
        soundpunch2(SoundData("sound/punch-2.mp3")),
        soundpunch3(SoundData("sound/punch-3.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}