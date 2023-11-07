package com.bettilt.mobile.pt.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.bettilt.mobile.pt.game.manager.AudioManager
import com.bettilt.mobile.pt.game.manager.SoundManager
import com.bettilt.mobile.pt.game.utils.runGDX

class SoundUtil {

    // Common
    val BOM    = SoundManager.EnumSound.BOM.data.sound
    val TOUCH  = SoundManager.EnumSound.TOUCH.data.sound
    val CLUNK  = SoundManager.EnumSound.CLUNK.data.sound

//    val listok by lazy { listOf(
//                "t5" to SoundManager.EnumSound.t5.data.sound,
//    ) }

    var volumeLevel = AudioManager.volumeLevelPercent / 100f

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, cof: Int = 1) = runGDX { if (isPause.not()) sound.play(volumeLevel/cof) }
}