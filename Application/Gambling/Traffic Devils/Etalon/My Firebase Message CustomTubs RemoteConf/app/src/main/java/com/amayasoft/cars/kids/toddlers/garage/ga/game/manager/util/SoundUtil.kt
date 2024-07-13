package com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.util

import com.badlogic.gdx.audio.Sound
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.AudioManager
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.runGDX
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.SoundManager

class SoundUtil {

    val mouse_click = SoundManager.EnumSound.mouse_click.data.sound
    val collect     = SoundManager.EnumSound.collect.data.sound

    var volumeLevel = AudioManager.volumeLevelPercent

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX { if (isPause.not()) sound.play(volume / 100f) }
}