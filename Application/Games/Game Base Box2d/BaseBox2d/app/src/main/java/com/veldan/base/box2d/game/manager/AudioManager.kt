package com.veldan.base.box2d.game.manager

import android.content.Context
import android.media.AudioManager
import com.veldan.base.box2d.appContext

object AudioManager {

    val audioManager      = com.veldan.base.box2d.appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolumeLevel    = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumeLevel get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel = (maxVolumeLevel / 100f)

    val volumeLevelPercent get() = (volumeLevel / onePercentVolumeLevel).toInt()

}