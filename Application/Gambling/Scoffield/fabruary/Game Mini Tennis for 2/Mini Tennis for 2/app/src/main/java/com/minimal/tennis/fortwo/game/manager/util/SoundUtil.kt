package com.minimal.tennis.fortwo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.minimal.tennis.fortwo.game.manager.AudioManager
import com.minimal.tennis.fortwo.game.manager.SoundManager
import com.minimal.tennis.fortwo.game.utils.runGDX
class SoundUtil {

    // Common
    val gool            = SoundManager.EnumSound.gool.data.sound
    val tennis_ball_hit = SoundManager.EnumSound.tennis_ball_hit.data.sound
    val tennis_border   = SoundManager.EnumSound.tennis_border.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

