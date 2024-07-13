package com.bitmango.go.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.bitmango.go.game.manager.AudioManager
import com.bitmango.go.game.utils.runGDX
import com.bitmango.go.game.manager.SoundManager

class SoundUtil {

    val click = SoundManager.EnumSound.click.data.sound
    val choice  = SoundManager.EnumSound.choice.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}