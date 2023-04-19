package com.veldan.junglego.assets.util

import com.veldan.junglego.assets.SoundManager
import com.veldan.junglego.manager.AudioManager
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {

    val CLICK get() = SoundManager.EnumSound.CLICK.soundData.sound
    val CHECK get() = SoundManager.EnumSound.CHECK.soundData.sound
    val WIN   get() = SoundManager.EnumSound.WIN.soundData.sound
    val FAIL  get() = SoundManager.EnumSound.FAIL.soundData.sound

    val soundList get() = listOf(CLICK, CHECK, WIN, FAIL)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100 / 100f)

    val isPause get() = (volumeLevel.value < 0f)

}