package qbl.bisriymyach.QuickBall.tidams

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound

class sosisochki_na_grili(var assetManager: AssetManager) {

    var loadableSoundList = mutableListOf<SoundData>()

    fun load() {
        loadableSoundList.onEach { assetManager.load(it.path, Sound::class.java) }
    }

    fun init() {
        loadableSoundList.onEach { it.sound = assetManager[it.path, Sound::class.java] }
        loadableSoundList.clear()
    }

    enum class EnumSound(val data: SoundData) {
        bum     (SoundData("vizok/bum.mp3")),
        clk     (SoundData("vizok/clk.mp3")),
        funil   (SoundData("vizok/funil.mp3")),
        podrank (SoundData("vizok/podrank.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}