package com.bandagames.mpuzzle.g.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.bandagames.mpuzzle.g.game.manager.AudioManager
import com.bandagames.mpuzzle.g.game.utils.runGDX
import com.bandagames.mpuzzle.g.game.manager.SoundManager

class SoundUtil {

    val uik = SoundManager.EnumSound.uik.data.sound
    val ake = SoundManager.EnumSound.ake.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}