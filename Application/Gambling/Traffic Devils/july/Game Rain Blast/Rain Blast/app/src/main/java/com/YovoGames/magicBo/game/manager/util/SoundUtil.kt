package com.YovoGames.magicBo.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.YovoGames.magicBo.game.manager.AudioManager
import com.YovoGames.magicBo.game.utils.runGDX
import com.YovoGames.magicBo.game.manager.SoundManager

class SoundUtil {

    val click  = SoundManager.EnumSound.click.data.sound
    private val take   = SoundManager.EnumSound.take.data.sound
    private val take_2 = SoundManager.EnumSound.take_2.data.sound

    val takeList = listOf(take, take_2)

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}