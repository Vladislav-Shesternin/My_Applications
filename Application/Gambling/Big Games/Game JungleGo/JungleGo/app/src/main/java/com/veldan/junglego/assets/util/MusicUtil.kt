package com.veldan.junglego.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.veldan.junglego.assets.MusicManager
import com.veldan.junglego.manager.AudioManager
import com.veldan.junglego.utils.Once
import com.veldan.junglego.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object MusicUtil: Disposable {

    private val coroutineVolumeLevel = CoroutineScope(Dispatchers.Default)

    val MAIN       get() = MusicManager.EnumMusic.MAIN.musicData.music
    val SPIN       get() = MusicManager.EnumMusic.SPIN.musicData.music
    val MINI_GAME  get() = MusicManager.EnumMusic.MINI_GAME.musicData.music
    val SUPER_GAME  get() = MusicManager.EnumMusic.SUPER_GAME.musicData.music

    val musicList get() = listOf(MAIN, SPIN, MINI_GAME, SUPER_GAME)

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
            }
        }



    init {
        collectVolumeLevel()
    }



    override fun dispose() {
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