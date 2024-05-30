package com.rochevasternin.physical.joints.game.manager.util

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.rochevasternin.physical.joints.game.manager.AudioManager
import com.rochevasternin.physical.joints.game.manager.MusicManager
import com.rochevasternin.physical.joints.game.utils.runGDX
import com.rochevasternin.physical.joints.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MusicUtil: Disposable {

    private val coroutine = CoroutineScope(Dispatchers.Default)

    val song_1 = MusicManager.EnumMusic.song_1.data.music
    val song_2 = MusicManager.EnumMusic.song_2.data.music
    val song_3 = MusicManager.EnumMusic.song_3.data.music
    val song_4 = MusicManager.EnumMusic.song_4.data.music
    val song_5 = MusicManager.EnumMusic.song_5.data.music
    val song_6 = MusicManager.EnumMusic.song_6.data.music
    val song_7 = MusicManager.EnumMusic.song_7.data.music
    val song_8 = MusicManager.EnumMusic.song_8.data.music
    val song_9 = MusicManager.EnumMusic.song_9.data.music

    val musicList = listOf(song_1, song_2, song_3, song_4, song_5, song_6, song_7, song_8, song_9)

    // 0..100
    val volumeLevelFlow = MutableStateFlow(AudioManager.volumeLevelPercent / 100f)

    private var _music: Music? = null
    var music: Music?
        get() = _music
        set(value) { runGDX {
            if (value != null) {
                if (_music != value) {
                    _music?.stop()
                    _music = value
                    _music?.volume = volumeLevelFlow.value
                    _music?.play()
                }
            } else {
                _music?.stop()
                _music = null
            }
        } }

    init {
        coroutine.launch { volumeLevelFlow.collect { level -> runGDX { _music?.volume = level } } }
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutine)
        _music = null
        music  = null
    }

}