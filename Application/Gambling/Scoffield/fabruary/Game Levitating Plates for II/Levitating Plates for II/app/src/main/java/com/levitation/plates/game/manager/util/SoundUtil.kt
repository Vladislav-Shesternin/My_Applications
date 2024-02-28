package com.levitation.plates.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.levitation.plates.game.manager.AudioManager
import com.levitation.plates.game.manager.SoundManager
import com.levitation.plates.game.utils.runGDX
class SoundUtil {

    // Common
    val futuristic_smg_sound_effect_win = SoundManager.EnumSound.futuristic_smg_sound_effect_win.data.sound
    val plasmablaster_udar              = SoundManager.EnumSound.plasmablaster_udar.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

