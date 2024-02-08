package com.fortunetiger.carnival.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.fortunetiger.carnival.game.manager.AudioManager
import com.fortunetiger.carnival.game.manager.SoundManager
import com.fortunetiger.carnival.game.utils.runGDX
class SoundUtil {

    // Common
    val bass_drop = SoundManager.EnumSound.bass_drop.data.sound
    val click     = SoundManager.EnumSound.click.data.sound
    val fail      = SoundManager.EnumSound.fail.data.sound
    val touch     = SoundManager.EnumSound.touch.data.sound
    val win       = SoundManager.EnumSound.win.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

