package fortune.tiger.avia.game.manager

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
        FUILE(         SoundData("sound/fuile.mp3")),
        KLOC(          SoundData("sound/kloc.mp3")),
        PRILIP(        SoundData("sound/prilip.mp3")),
        STAR_PEREMOGA( SoundData("sound/star_peremoga.mp3")),
        STRS_PROIGRAL( SoundData("sound/strs_proigral.mp3")),
    }

    data class SoundData(
        val path: String,
    ) {
        lateinit var sound: Sound
    }

}