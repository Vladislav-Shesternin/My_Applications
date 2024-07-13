package com.bigag.farm.garden.village.offline.farming.game.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.bigag.farm.garden.village.offline.farming.game.game.manager.AudioManager
import com.bigag.farm.garden.village.offline.farming.game.game.utils.runGDX
import com.bigag.farm.garden.village.offline.farming.game.game.manager.SoundManager

class SoundUtil {

    val click = SoundManager.EnumSound.click.data.sound
    val bonus = SoundManager.EnumSound.bonus.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}