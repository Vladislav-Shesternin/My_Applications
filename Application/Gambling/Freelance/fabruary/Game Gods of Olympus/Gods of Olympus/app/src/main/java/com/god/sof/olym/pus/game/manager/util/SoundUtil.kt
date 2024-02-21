package com.god.sof.olym.pus.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.god.sof.olym.pus.game.manager.AudioManager
import com.god.sof.olym.pus.game.manager.SoundManager
import com.god.sof.olym.pus.game.utils.runGDX
class SoundUtil {

    // Common
    val failuredrumsoundeffect = SoundManager.EnumSound.failuredrumsoundeffect.data.sound
    val poimal                 = SoundManager.EnumSound.poimal.data.sound
    val popup                  = SoundManager.EnumSound.popup.data.sound
    val winner                 = SoundManager.EnumSound.winner.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

