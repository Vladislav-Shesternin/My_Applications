package rainbowriches.lucky.start.game.manager.util

import com.badlogic.gdx.audio.Sound
import rainbowriches.lucky.start.game.manager.AudioManager
import rainbowriches.lucky.start.game.manager.SoundManager
import rainbowriches.lucky.start.game.utils.runGDX

class SoundUtil {

    // Common
    val PUT      = SoundManager.EnumSound.PUT.data.sound
    val POSITIVE = SoundManager.EnumSound.POSITIVE.data.sound
    val NEGATIVE = SoundManager.EnumSound.NEGATIVE.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}