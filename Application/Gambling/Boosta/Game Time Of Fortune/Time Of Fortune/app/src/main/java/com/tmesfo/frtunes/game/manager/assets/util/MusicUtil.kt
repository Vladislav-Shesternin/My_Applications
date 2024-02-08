package com.tmesfo.frtunes.game.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.tmesfo.frtunes.game.manager.AudioManager
import com.tmesfo.frtunes.game.manager.assets.MusicManager
import com.tmesfo.frtunes.util.cancelCoroutinesAll

object MusicUtil: Disposable {

    private val coroutineVolumeLevel = CoroutineScope(Dispatchers.Default)


    val MAIN       get() = MusicManager.EnumMusic.MAIN.data.music

    val musicList get() = listOf(MAIN)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100)

    var isPause = false

    private var previousMusic: Music? = null
    var currentMusic: Music?
        get() = previousMusic
        set(value) {
            Gdx.app.postRunnable {
                if (isPause) return@postRunnable

                if (previousMusic != value) {
                    previousMusic?.stop()
                    previousMusic = value
                    previousMusic?.play()
                }

                previousMusic?.isLooping = true
            }
        }



    init {
        collectVolumeLevel()
    }



    override fun dispose() {
        previousMusic = null
        cancelCoroutinesAll(coroutineVolumeLevel)
    }



    private fun collectVolumeLevel() {
        var volume: Float

        coroutineVolumeLevel.launch { volumeLevel.collect { level ->
            volume = (level / 100f)
            isPause = (volume < 0f)
            musicList.onEach { music -> music.volume = volume }
        } }
    }

}