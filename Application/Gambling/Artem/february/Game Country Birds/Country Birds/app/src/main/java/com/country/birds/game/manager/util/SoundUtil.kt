package com.country.birds.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.country.birds.game.manager.AudioManager
import com.country.birds.game.manager.SoundManager
import com.country.birds.game.utils.runGDX
class SoundUtil {

    val fail                = SoundManager.EnumSound.fail.data.sound
    val punch               = SoundManager.EnumSound.punch.data.sound
    val swing_whoosh_weapon = SoundManager.EnumSound.swing_whoosh_weapon.data.sound
    val win                 = SoundManager.EnumSound.win.data.sound

    // 0..100
    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

