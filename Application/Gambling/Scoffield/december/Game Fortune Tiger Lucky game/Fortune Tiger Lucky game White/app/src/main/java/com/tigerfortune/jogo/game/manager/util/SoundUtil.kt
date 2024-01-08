package com.tigerfortune.jogo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.tigerfortune.jogo.game.manager.AudioManager
import com.tigerfortune.jogo.game.manager.SoundManager
import com.tigerfortune.jogo.game.utils.runGDX
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

