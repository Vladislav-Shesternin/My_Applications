package tigerfortune.lucky.game.game.manager.util

import com.badlogic.gdx.audio.Sound
import tigerfortune.lucky.game.game.manager.AudioManager
import tigerfortune.lucky.game.game.manager.SoundManager
import tigerfortune.lucky.game.game.utils.runGDX

class SoundUtil {

    // Common
    val s_click = SoundManager.EnumSound.s_click.data.sound
    val s_lose  = SoundManager.EnumSound.s_lose.data.sound
    val s_bonus = SoundManager.EnumSound.s_bonus.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}