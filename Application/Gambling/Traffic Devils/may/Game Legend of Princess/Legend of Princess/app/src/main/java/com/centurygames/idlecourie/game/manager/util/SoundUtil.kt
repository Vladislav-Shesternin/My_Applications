package com.centurygames.idlecourie.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.centurygames.idlecourie.game.manager.AudioManager
import com.centurygames.idlecourie.game.utils.runGDX
import com.centurygames.idlecourie.game.manager.SoundManager

class SoundUtil {

    val pop = SoundManager.EnumSound.pop.data.sound
    val violin_win = SoundManager.EnumSound.violin_win.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}