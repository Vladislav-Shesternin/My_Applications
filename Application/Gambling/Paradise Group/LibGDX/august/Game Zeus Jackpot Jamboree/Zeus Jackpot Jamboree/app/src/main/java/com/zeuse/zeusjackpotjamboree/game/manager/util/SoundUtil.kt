package com.zeuse.zeusjackpotjamboree.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.zeuse.zeusjackpotjamboree.game.manager.AudioManager
import com.zeuse.zeusjackpotjamboree.game.manager.SoundManager
import com.zeuse.zeusjackpotjamboree.game.utils.runGDX
import com.zeuse.zeusjackpotjamboree.util.log

class SoundUtil {

    // Collision
    val ITEM   = SoundManager.EnumSound.ITEM.data.sound
    val BORDER = SoundManager.EnumSound.BORDER.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    private var previousSoundId = 0L

    fun play(sound: Sound, isStopPrev: Boolean = true) = runGDX {
        if (isPause.not()) {
            if (isStopPrev && previousSoundId != 0L) sound.stop(previousSoundId)
            previousSoundId = sound.play(volumeLevel)
        }
    }
}