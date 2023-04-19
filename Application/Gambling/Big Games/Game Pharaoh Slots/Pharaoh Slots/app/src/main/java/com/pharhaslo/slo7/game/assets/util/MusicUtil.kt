@file:Suppress("GDXKotlinStaticResource")

package com.pharhaslo.slo7.game.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.pharhaslo.slo7.game.assets.MusicManager
import com.pharhaslo.slo7.game.manager.AudioManager
import com.pharhaslo.slo7.game.utils.cancelCoroutinesAll
import com.pharhaslo.slo7.game.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object MusicUtil: Disposable {

    private val coroutineVolumeLevel get() = CoroutineScope(Dispatchers.Default)

    val MAIN       get() = MusicManager.EnumMusic.MAIN.data.music
    val SPIN       get() = MusicManager.EnumMusic.GO.data.music
    val SUPER_GAME get() = MusicManager.EnumMusic.SUPER_GAME.data.music

    val musicList get() = listOf(MAIN, SPIN, SUPER_GAME)

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