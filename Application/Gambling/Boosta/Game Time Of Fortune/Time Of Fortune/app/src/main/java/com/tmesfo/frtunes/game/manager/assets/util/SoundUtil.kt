package com.tmesfo.frtunes.game.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import kotlinx.coroutines.flow.MutableStateFlow
import com.tmesfo.frtunes.game.manager.AudioManager
import com.tmesfo.frtunes.game.manager.assets.SoundManager

object SoundUtil {

    val CLICK get() = SoundManager.EnumSound.CLICK.data.sound
    val WIN   get() = SoundManager.EnumSound.WIN.data.sound

    val soundList get() = listOf(CLICK, WIN)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100 / 100f)

    val isPause get() = (volumeLevel.value < 0f)

}

var previousSoundId = 0L

fun Sound.playAdvanced() {
    Gdx.app.postRunnable { with(SoundUtil) {
        if (isPause.not()) {
            if (previousSoundId != 0L) stop(previousSoundId)
            previousSoundId = play(volumeLevel.value)
        }
    } }
}

