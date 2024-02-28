package com.boo.koftre.sure.game.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.boo.koftre.sure.game.game.manager.AudioManager
import com.boo.koftre.sure.game.game.manager.SoundManager
import com.boo.koftre.sure.game.game.utils.runGDX

class SoundUtil {

    // Common
    val good        = SoundManager.EnumSound.good.data.sound
    val hitting_the = SoundManager.EnumSound.hitting_the.data.sound
    val pipe_f      = SoundManager.EnumSound.pipe_f.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}