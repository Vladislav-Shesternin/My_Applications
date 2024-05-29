package com.orange.magic.board.doodle.color.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.orange.magic.board.doodle.color.game.manager.AudioManager
import com.orange.magic.board.doodle.color.game.utils.runGDX
import com.orange.magic.board.doodle.color.game.manager.SoundManager

class SoundUtil {

    val barier = SoundManager.EnumSound.barier.data.sound
    val clik   = SoundManager.EnumSound.clik.data.sound
    val si     = SoundManager.EnumSound.si.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}