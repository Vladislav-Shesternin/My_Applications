package com.hepagame.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.hepagame.game.manager.AudioManager
import com.hepagame.game.utils.runGDX
import com.hepagame.game.manager.SoundManager

class SoundUtil {

    val flap = SoundManager.EnumSound.flap.data.sound
    val hit = SoundManager.EnumSound.hit.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, coff: Float = 1f) = runGDX { if (isPause.not()) sound.play((volumeLevel / 100f) * coff) }
}