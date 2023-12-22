package newyearpuz.lessons.forditky.game.manager.util

import com.badlogic.gdx.audio.Sound
import newyearpuz.lessons.forditky.game.manager.AudioManager
import newyearpuz.lessons.forditky.game.manager.SoundManager
import newyearpuz.lessons.forditky.game.utils.runGDX

class SoundUtil {

    val click  = SoundManager.EnumSound.click.data.sound
    val win  = SoundManager.EnumSound.win.data.sound
    val touchel  = SoundManager.EnumSound.touchel.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}