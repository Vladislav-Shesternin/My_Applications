package com.superstar.superspring.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.superstar.superspring.game.manager.AudioManager
import com.superstar.superspring.game.manager.SoundManager
import com.superstar.superspring.game.utils.runGDX
class SoundUtil {


    val jump = SoundManager.EnumSound.jump.data.sound
    val star = SoundManager.EnumSound.star.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

