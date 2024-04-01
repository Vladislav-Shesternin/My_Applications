package com.winterria.jumpilo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.winterria.jumpilo.game.manager.AudioManager
import com.winterria.jumpilo.game.manager.SoundManager
import com.winterria.jumpilo.game.utils.runGDX
class SoundUtil {


    val CORRECT      = SoundManager.EnumSound.CORRECT.data.sound
    val JUMP_C       = SoundManager.EnumSound.JUMP_C.data.sound
    val PUNCH_BOXING = SoundManager.EnumSound.PUNCH_BOXING.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

