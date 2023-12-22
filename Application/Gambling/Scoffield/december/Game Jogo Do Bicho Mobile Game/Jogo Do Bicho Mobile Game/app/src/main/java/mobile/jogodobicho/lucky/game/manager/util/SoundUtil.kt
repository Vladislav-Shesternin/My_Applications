package mobile.jogodobicho.lucky.game.manager.util

import com.badlogic.gdx.audio.Sound
import mobile.jogodobicho.lucky.game.manager.AudioManager
import mobile.jogodobicho.lucky.game.manager.SoundManager
import mobile.jogodobicho.lucky.game.utils.runGDX

class SoundUtil {

    // Common
    val click_game_menu      = SoundManager.EnumSound.click_game_menu.data.sound
    val fail = SoundManager.EnumSound.fail.data.sound
    val win = SoundManager.EnumSound.win.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel) }
}