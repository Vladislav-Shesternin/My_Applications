package com.destroyer.buildings.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.destroyer.buildings.game.manager.AudioManager
import com.destroyer.buildings.game.manager.SoundManager
import com.destroyer.buildings.game.utils.runGDX
class SoundUtil {

    val PUNCH = SoundManager.EnumSound.PUNCH.data.sound
    val WATER = SoundManager.EnumSound.WATER.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

