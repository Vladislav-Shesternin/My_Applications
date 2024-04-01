package com.favsport.slots.utils

import android.content.Context
import android.media.AudioManager
import com.favsport.slots.activityContext
import com.favsport.slots.utils.AudioManagerUtil.audioManager

object AudioManagerUtil {

    val audioManager = activityContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    val onePercent = maxVolumeLevel / 100f



    fun setAudioVolume(volume: Int){
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
    }

}