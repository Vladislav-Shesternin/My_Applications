package com.duckduckmoosedesign.cpk.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.duckduckmoosedesign.cpk.game.manager.AudioManager
import com.duckduckmoosedesign.cpk.game.utils.runGDX
import com.duckduckmoosedesign.cpk.game.manager.SoundManager

class SoundUtil {

    val mouse_click = SoundManager.EnumSound.click.data.sound
    val collect     = SoundManager.EnumSound.boom.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}