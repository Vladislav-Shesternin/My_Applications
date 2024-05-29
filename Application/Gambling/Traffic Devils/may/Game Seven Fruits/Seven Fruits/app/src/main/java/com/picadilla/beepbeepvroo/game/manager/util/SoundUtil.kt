package com.picadilla.beepbeepvroo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.picadilla.beepbeepvroo.game.manager.AudioManager
import com.picadilla.beepbeepvroo.game.utils.runGDX
import com.picadilla.beepbeepvroo.game.manager.SoundManager

class SoundUtil {

    val barier = SoundManager.EnumSound.barier.data.sound
    val clik   = SoundManager.EnumSound.clik.data.sound
    val si     = SoundManager.EnumSound.si.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}