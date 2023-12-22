package newyearpuz.lessons.forditky.game.manager

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
        click(     SoundData("sound/click.mp3")     ),
        win(     SoundData("sound/win.mp3")     ),
        touchel(     SoundData("sound/touchel.mp3")     ),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}