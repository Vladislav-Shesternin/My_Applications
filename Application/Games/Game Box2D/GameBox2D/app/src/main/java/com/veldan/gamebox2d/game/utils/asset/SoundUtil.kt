package com.veldan.gamebox2d.game.utils.asset

import com.badlogic.gdx.audio.Sound
import com.veldan.gamebox2d.game.manager.AudioManager
import com.veldan.gamebox2d.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {

//    val CLICK get() = SoundManager.EnumSound.CLICK.data.sound
//    val STAR  get() = SoundManager.EnumSound.STAR.data.sound
//    val STONE get() = SoundManager.EnumSound.STONE.data.sound
//
//    val soundList = listOf(CLICK, STAR, STONE)
//
    val volumeLevelFlow = MutableStateFlow(AudioManager.volumeLevelPercent / 100f)

    val isPause get() = (volumeLevelFlow.value <= 0f)
}

var previousSoundId = 0L
    private set

fun Sound.playAdvanced() { runGDX { with(SoundUtil) {
    if (isPause.not()) {
        if (previousSoundId != 0L) stop(previousSoundId)
        previousSoundId = play(volumeLevelFlow.value)
    }
} } }