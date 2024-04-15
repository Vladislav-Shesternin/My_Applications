package com.rochevasternin.physical.joints.game.manager

import android.content.Context
import android.media.AudioManager

object AudioManager {

    val audioManager   by lazy { com.rochevasternin.physical.joints.appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    val maxVolumeLevel by lazy { audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) }
    val volumeLevel    get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel by lazy { (maxVolumeLevel / 100f) }
    val volumeLevelPercent    get() = volumeLevel / onePercentVolumeLevel

}