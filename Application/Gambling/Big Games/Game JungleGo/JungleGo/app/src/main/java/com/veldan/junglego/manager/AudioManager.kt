package com.veldan.junglego.manager

import android.content.Context
import android.media.AudioManager
import com.veldan.junglego.activityContext
import com.veldan.junglego.utils.log

object AudioManager {

    val audioManager = activityContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val volumeLevel get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel = (maxVolumeLevel / 100f)

    val volumeLevelFrom_0_to_100 get() = (volumeLevel / onePercentVolumeLevel).toInt()

}