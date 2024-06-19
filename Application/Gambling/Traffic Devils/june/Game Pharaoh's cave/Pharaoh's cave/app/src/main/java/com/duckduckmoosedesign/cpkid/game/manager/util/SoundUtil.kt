package com.duckduckmoosedesign.cpkid.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.duckduckmoosedesign.cpkid.game.manager.AudioManager
import com.duckduckmoosedesign.cpkid.game.utils.runGDX
import com.duckduckmoosedesign.cpkid.game.manager.SoundManager

class SoundUtil {

    val pip = SoundManager.EnumSound.cip.data.sound
    val cip = SoundManager.EnumSound.pip.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}