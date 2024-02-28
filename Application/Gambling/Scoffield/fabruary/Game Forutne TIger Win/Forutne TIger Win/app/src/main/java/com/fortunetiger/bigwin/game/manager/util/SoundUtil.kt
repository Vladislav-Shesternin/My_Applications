package com.fortunetiger.bigwin.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.fortunetiger.bigwin.game.manager.AudioManager
import com.fortunetiger.bigwin.game.manager.SoundManager
import com.fortunetiger.bigwin.game.utils.runGDX
class SoundUtil {

    // Common
    val bum          = SoundManager.EnumSound.bum.data.sound
    val click        = SoundManager.EnumSound.click.data.sound
    val coin_donatio = SoundManager.EnumSound.coin_donatio.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

