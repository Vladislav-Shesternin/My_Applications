package com.my.cooking.chef.kitchen.craze.fe.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.my.cooking.chef.kitchen.craze.fe.game.manager.AudioManager
import com.my.cooking.chef.kitchen.craze.fe.game.utils.runGDX
import com.my.cooking.chef.kitchen.craze.fe.game.manager.SoundManager

class SoundUtil {

    val click = SoundManager.EnumSound.click.data.sound
    val fail  = SoundManager.EnumSound.fail.data.sound
    val take  = SoundManager.EnumSound.take.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}