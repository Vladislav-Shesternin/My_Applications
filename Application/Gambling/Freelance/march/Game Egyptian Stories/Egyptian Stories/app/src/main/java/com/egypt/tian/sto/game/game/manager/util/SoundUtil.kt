package com.egypt.tian.sto.game.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.egypt.tian.sto.game.game.manager.AudioManager
import com.egypt.tian.sto.game.game.manager.SoundManager
import com.egypt.tian.sto.game.game.utils.runGDX

class SoundUtil {

    // Common
    val game     = SoundManager.EnumSound.game.data.sound
    val glass    = SoundManager.EnumSound.glass.data.sound
    val negative = SoundManager.EnumSound.negative.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}