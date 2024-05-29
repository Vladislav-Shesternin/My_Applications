package com.rusya.cartycoo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.rusya.cartycoo.game.manager.AudioManager
import com.rusya.cartycoo.game.utils.runGDX
import com.rusya.cartycoo.game.manager.SoundManager

class SoundUtil {

    val boom = SoundManager.EnumSound.boom.data.sound
    val clik = SoundManager.EnumSound.clik.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}