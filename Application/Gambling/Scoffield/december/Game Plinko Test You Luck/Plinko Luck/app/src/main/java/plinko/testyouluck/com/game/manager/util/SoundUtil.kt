package plinko.testyouluck.com.game.manager.util

import com.badlogic.gdx.audio.Sound
import plinko.testyouluck.com.game.manager.AudioManager
import plinko.testyouluck.com.game.manager.SoundManager
import plinko.testyouluck.com.game.utils.runGDX
class SoundUtil {

    // Common
    val mouse_click = SoundManager.EnumSound.mouse_click.data.sound
    val buy         = SoundManager.EnumSound.buy.data.sound
    val win         = SoundManager.EnumSound.win.data.sound
    val dot1        = SoundManager.EnumSound.dot1.data.sound
    val dot2        = SoundManager.EnumSound.dot2.data.sound
    val success     = SoundManager.EnumSound.success.data.sound
    val bomb        = SoundManager.EnumSound.bomb.data.sound

    val dot = listOf(dot1, dot2)

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

