package com.farello.rocketing.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.farello.rocketing.game.manager.AudioManager
import com.farello.rocketing.game.manager.SoundManager
import com.farello.rocketing.game.utils.runGDX
class SoundUtil {


    val BIMS = SoundManager.EnumSound.BIMS.data.sound
    val CRACKING1 = SoundManager.EnumSound.CRACKING1.data.sound
    val CRACKING2 = SoundManager.EnumSound.CRACKING2.data.sound

    val CRC = listOf(CRACKING1, CRACKING2)

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

