package com.mvgamesteam.mineta.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.mvgamesteam.mineta.game.manager.AudioManager
import com.mvgamesteam.mineta.game.utils.runGDX
import com.mvgamesteam.mineta.game.manager.SoundManager

class SoundUtil {

    val mouse_click = SoundManager.EnumSound.mouse_click.data.sound
    val collect     = SoundManager.EnumSound.collect.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}