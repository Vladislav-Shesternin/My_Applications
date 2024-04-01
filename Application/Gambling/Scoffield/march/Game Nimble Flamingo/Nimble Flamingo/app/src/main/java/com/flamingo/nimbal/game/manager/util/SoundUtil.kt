package com.flamingo.nimbal.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.flamingo.nimbal.game.manager.AudioManager
import com.flamingo.nimbal.game.manager.SoundManager
import com.flamingo.nimbal.game.utils.runGDX
class SoundUtil {

    val bonus = SoundManager.EnumSound.bonus.data.sound
    val error = SoundManager.EnumSound.error.data.sound
    val swipe = SoundManager.EnumSound.swipe.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

