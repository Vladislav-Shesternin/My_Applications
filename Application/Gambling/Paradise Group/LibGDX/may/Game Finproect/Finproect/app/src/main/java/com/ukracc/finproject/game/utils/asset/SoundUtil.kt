package com.ukracc.finproject.game.utils.asset

import com.badlogic.gdx.audio.Sound
import com.ukracc.finproject.game.manager.AudioManager
import com.ukracc.finproject.game.manager.SoundManager
import com.ukracc.finproject.game.utils.runGDX

class SoundUtil {

        val CLICK get() = SoundManager.EnumSound.CLICK.data.sound
//    val STAR  get() = SoundManager.EnumSound.STAR.data.sound
//    val STONE get() = SoundManager.EnumSound.STONE.data.sound
//
//    val soundList = listOf(CLICK, STAR, STONE)
//
    var volumeLevelPercent: Float = AudioManager.volumeLevelPercent.toFloat()

    var isPause = (volumeLevelPercent <= 0f)

    private var previousSoundId = 0L

    fun play(sound: Sound?) = runGDX { sound?.run {
        if (isPause.not()) {
            if (previousSoundId != 0L) stop(previousSoundId)
            previousSoundId = play(volumeLevelPercent / 100f)
        }
    } }
}