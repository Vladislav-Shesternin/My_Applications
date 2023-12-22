package plinko.gameballs.nine.game.manager.util

import com.badlogic.gdx.audio.Sound
import plinko.gameballs.nine.game.manager.AudioManager
import plinko.gameballs.nine.game.manager.SoundManager
import plinko.gameballs.nine.game.utils.runGDX

class SoundUtil {

    // Common
    val suddenly = SoundManager.EnumSound.soundsuddenly .data.sound
    val punch1   = SoundManager.EnumSound.soundpunch1.data.sound
    val punch2   = SoundManager.EnumSound.soundpunch2.data.sound
    val punch3   = SoundManager.EnumSound.soundpunch3.data.sound

    val punch = listOf(punch1, punch2, punch3)

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}