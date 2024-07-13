package com.kongregate.mobile.burrit.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.kongregate.mobile.burrit.game.manager.AudioManager
import com.kongregate.mobile.burrit.game.utils.runGDX
import com.kongregate.mobile.burrit.game.manager.SoundManager

class SoundUtil {

    val click = SoundManager.EnumSound.click.data.sound
    val item  = SoundManager.EnumSound.item.data.sound
    val SUPER  = SoundManager.EnumSound.SUPER.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}