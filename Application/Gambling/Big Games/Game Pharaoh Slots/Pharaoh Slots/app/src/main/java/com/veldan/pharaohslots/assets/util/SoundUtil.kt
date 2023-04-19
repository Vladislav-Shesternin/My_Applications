package com.veldan.pharaohslots.assets.util

import com.veldan.pharaohslots.assets.SoundManager
import com.veldan.pharaohslots.manager.AudioManager
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {

    val CLICK get() = SoundManager.EnumSound.CLICK.data.sound
    val CHECK get() = SoundManager.EnumSound.CHECK.data.sound
    val WIN   get() = SoundManager.EnumSound.WIN.data.sound

    val soundList get() = listOf(CLICK, CHECK, WIN)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100 / 100f)

    val isPause get() = (volumeLevel.value < 0f)

}