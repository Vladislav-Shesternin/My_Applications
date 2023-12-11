package lycky.fortune.tiger.game.manager.util

import com.badlogic.gdx.audio.Sound
import lycky.fortune.tiger.game.manager.AudioManager
import lycky.fortune.tiger.game.manager.SoundManager
import lycky.fortune.tiger.game.utils.runGDX

class SoundUtil {

    // Common
    val bad= SoundManager.EnumSound.bad.data.sound
    val bonus= SoundManager.EnumSound.bonus.data.sound
    val click= SoundManager.EnumSound.click.data.sound
    val select= SoundManager.EnumSound.select.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}