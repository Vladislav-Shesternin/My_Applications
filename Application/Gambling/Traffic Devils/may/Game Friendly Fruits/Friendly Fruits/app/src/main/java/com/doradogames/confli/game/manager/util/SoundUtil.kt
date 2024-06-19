package com.doradogames.confli.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.doradogames.confli.game.manager.AudioManager
import com.doradogames.confli.game.utils.runGDX
import com.doradogames.confli.game.manager.SoundManager

class SoundUtil {

    val fruit = SoundManager.EnumSound.fruit.data.sound
    val apple = SoundManager.EnumSound.apple.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound) = runGDX { if (isPause.not()) sound.play(volumeLevel / 100f) }
}