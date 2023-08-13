package com.tsabekaa.finhelper.game.manager

import android.content.Context
import android.media.AudioManager
import com.tsabekaa.finhelper.appContext

object AudioManager {

    val audioManager      = appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolumeLevel    = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumeLevel get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel = (maxVolumeLevel / 100f)

    val volumeLevelPercent get() = (volumeLevel / onePercentVolumeLevel).toInt()

}