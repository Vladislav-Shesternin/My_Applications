package com.fotune.tiger.slotthighrino.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.fotune.tiger.slotthighrino.game.manager.AudioManager
import com.fotune.tiger.slotthighrino.game.manager.SoundManager
import com.fotune.tiger.slotthighrino.game.utils.runGDX
class SoundUtil {

    val end  = SoundManager.EnumSound.end.data.sound
    val roll = SoundManager.EnumSound.roll.data.sound
    val spin = SoundManager.EnumSound.spin.data.sound
    val win  = SoundManager.EnumSound.win.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

