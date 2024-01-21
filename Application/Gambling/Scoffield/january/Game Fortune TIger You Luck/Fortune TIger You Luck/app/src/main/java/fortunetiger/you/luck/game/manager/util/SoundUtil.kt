package fortunetiger.you.luck.game.manager.util

import com.badlogic.gdx.audio.Sound
import fortunetiger.you.luck.game.manager.AudioManager
import fortunetiger.you.luck.game.manager.SoundManager
import fortunetiger.you.luck.game.utils.runGDX

class SoundUtil {

    // Common
    val ClickR      = SoundManager.EnumSound.Click.data.sound
    val POSITIVE = SoundManager.EnumSound.POSITIVE.data.sound
    val NEGATIVE = SoundManager.EnumSound.NEGATIVE.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}