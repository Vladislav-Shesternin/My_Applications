package com.lohina.titantreasuretrove.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.lohina.titantreasuretrove.game.manager.AudioManager
import com.lohina.titantreasuretrove.game.manager.SoundManager
import com.lohina.titantreasuretrove.game.utils.runGDX
import com.lohina.titantreasuretrove.util.log

class SoundUtil {

    // Collision
    val ITEM   = SoundManager.EnumSound.ITEM.data.sound
    val AY = SoundManager.EnumSound.AY.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    private var previousSoundId = 0L

    fun play(sound: Sound, isStopPrev: Boolean = false) = runGDX {
        if (isPause.not()) {
            if (isStopPrev && previousSoundId != 0L) sound.stop(previousSoundId)
            previousSoundId = sound.play(0.3f)
        }
    }
}