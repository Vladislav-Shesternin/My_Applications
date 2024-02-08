package jogos.tigerfortune.tighrino.game.manager

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
        bomb            (SoundData("sound/bomb.mp3")),
        caught          (SoundData("sound/caught.mp3")),
        fail            (SoundData("sound/fail.mp3")),
        level_passed    (SoundData("sound/level-passed.mp3")),
        soft_click_trial(SoundData("sound/click.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}