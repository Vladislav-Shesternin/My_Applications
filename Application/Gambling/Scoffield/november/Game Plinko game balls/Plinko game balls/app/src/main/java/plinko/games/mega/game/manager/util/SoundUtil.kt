package plinko.games.mega.game.manager.util

import com.badlogic.gdx.audio.Sound
import plinko.games.mega.game.manager.AudioManager
import plinko.games.mega.game.manager.SoundManager
import plinko.games.mega.game.utils.runGDX
import plinko.games.mega.util.log

class SoundUtil {

    // Common
    val CLICK  = SoundManager.EnumSound.CLICK.data.sound
    val UDAR1  = SoundManager.EnumSound.UDAR1.data.sound
    val UDAR2  = SoundManager.EnumSound.UDAR2.data.sound
    val UDAR3  = SoundManager.EnumSound.UDAR3.data.sound
    val UDAR4  = SoundManager.EnumSound.UDAR4.data.sound

    val udars = listOf(UDAR1, UDAR2, UDAR3, UDAR4)

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}