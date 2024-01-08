package aviator.original.win.game.manager.util

import com.badlogic.gdx.audio.Sound
import aviator.original.win.game.manager.AudioManager
import aviator.original.win.game.manager.SoundManager
import aviator.original.win.game.utils.runGDX
class SoundUtil {

    // Common
    val bomb             = SoundManager.EnumSound.bomb.data.sound
    val caught           = SoundManager.EnumSound.caught.data.sound
    val fail             = SoundManager.EnumSound.fail.data.sound
    val level_passed     = SoundManager.EnumSound.level_passed.data.sound
    val soft_click_trial = SoundManager.EnumSound.soft_click_trial.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

