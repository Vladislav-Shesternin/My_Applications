package com.gentle.spring.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.gentle.spring.game.manager.AudioManager
import com.gentle.spring.game.manager.SoundManager
import com.gentle.spring.game.utils.runGDX
class SoundUtil {


    val BUBBLEBEAM = SoundManager.EnumSound.BUBBLEBEAM.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

