package com.dogbytegames.offtheroa.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.dogbytegames.offtheroa.game.manager.AudioManager
import com.dogbytegames.offtheroa.game.utils.runGDX
import com.dogbytegames.offtheroa.game.manager.SoundManager

class SoundUtil {

    val click = SoundManager.EnumSound.click.data.sound
    val bonus = SoundManager.EnumSound.bonus.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}