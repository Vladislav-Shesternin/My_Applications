package com.flaregames.olympusrisin.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.flaregames.olympusrisin.game.manager.AudioManager
import com.flaregames.olympusrisin.game.utils.runGDX
import com.flaregames.olympusrisin.game.manager.SoundManager

class SoundUtil {

    val boom = SoundManager.EnumSound.boom.data.sound
    val clik = SoundManager.EnumSound.clik.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}