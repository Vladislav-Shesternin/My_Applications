package qbl.bisriymyach.QuickBall.fastergan

import android.content.Context
import android.media.AudioManager
import qbl.bisriymyach.QuickBall.hotvils.kalimatronika

object gugols {

    val giga by lazy { kalimatronika.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    val olika by lazy { giga.getStreamMaxVolume(AudioManager.STREAM_MUSIC) }
    val hhdh get() = giga.getStreamVolume(AudioManager.STREAM_MUSIC)
    val monika by lazy { (olika / 100f) }
    val vodelfinkaumeLelP get() = hhdh / monika

}