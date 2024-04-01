package com.legojum.kangarooper.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.legojum.kangarooper.game.manager.AudioManager
import com.legojum.kangarooper.game.manager.SoundManager
import com.legojum.kangarooper.game.utils.runGDX
class SoundUtil {

    val JUMP = SoundManager.EnumSound.JUMP.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

