package com.phara.ohegy.ptgame.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.phara.ohegy.ptgame.game.manager.AudioManager
import com.phara.ohegy.ptgame.game.manager.SoundManager
import com.phara.ohegy.ptgame.game.utils.runGDX

class SoundUtil {

    val CLICK = SoundManager.EnumSound.CLICK.data.sound
    val LOSE  = SoundManager.EnumSound.LOSE.data.sound
    val WIN   = SoundManager.EnumSound.WIN.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}