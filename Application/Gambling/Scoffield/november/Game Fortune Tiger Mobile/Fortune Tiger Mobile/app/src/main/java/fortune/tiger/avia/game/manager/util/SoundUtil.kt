package fortune.tiger.avia.game.manager.util

import com.badlogic.gdx.audio.Sound
import fortune.tiger.avia.game.manager.AudioManager
import fortune.tiger.avia.game.manager.SoundManager
import fortune.tiger.avia.game.utils.runGDX

class SoundUtil {

    // Common
    val FUILE         = SoundManager.EnumSound.FUILE.data.sound
    val KLOC          = SoundManager.EnumSound.KLOC.data.sound
    val PRILIP        = SoundManager.EnumSound.PRILIP.data.sound
    val STAR_PEREMOGA = SoundManager.EnumSound.STAR_PEREMOGA.data.sound
    val STRS_PROIGRAL = SoundManager.EnumSound.STRS_PROIGRAL.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}