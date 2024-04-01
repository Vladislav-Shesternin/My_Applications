package com.guardians.galaxyguano.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.guardians.galaxyguano.game.manager.AudioManager
import com.guardians.galaxyguano.game.manager.SoundManager
import com.guardians.galaxyguano.game.utils.runGDX
class SoundUtil {


    val ASTEROID = SoundManager.EnumSound.ASTEROID.data.sound
    val PLATFORM = SoundManager.EnumSound.PLATFORM.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

