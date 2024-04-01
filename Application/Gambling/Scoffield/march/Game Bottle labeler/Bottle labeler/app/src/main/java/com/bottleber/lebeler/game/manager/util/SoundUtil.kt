package com.bottleber.lebeler.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.bottleber.lebeler.game.manager.AudioManager
import com.bottleber.lebeler.game.manager.SoundManager
import com.bottleber.lebeler.game.utils.runGDX
class SoundUtil {

    val GLASS = SoundManager.EnumSound.GLASS.data.sound
    val GUN   = SoundManager.EnumSound.GUN.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

