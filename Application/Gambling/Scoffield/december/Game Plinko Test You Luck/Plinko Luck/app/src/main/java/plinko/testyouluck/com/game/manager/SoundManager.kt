package plinko.testyouluck.com.game.manager

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
        mouse_click( SoundData("sound/mouse-click.mp3") ),
        buy(         SoundData("sound/buy.mp3")         ),
        dot1(        SoundData("sound/dot1.mp3")        ),
        dot2(        SoundData("sound/dot2.mp3")        ),
        win(         SoundData("sound/win.mp3")         ),
        success(     SoundData("sound/success.mp3")     ),
        bomb(        SoundData("sound/bomb.mp3")        ),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}