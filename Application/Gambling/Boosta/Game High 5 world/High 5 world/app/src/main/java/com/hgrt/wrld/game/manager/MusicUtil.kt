package com.hgrt.wrld.game.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MusicUtil: Disposable {

    private val coroutine = CoroutineScope(Dispatchers.Default)

    val MUSIC = MusicManager.EnumMusic.MUSIC.data.music

    // 0..100
    val volumeLevelFlow = MutableStateFlow(AudioManager.volumeLevelPercent)

    private var _music: Music? = null
    var music: Music?
        get() = _music
        set(value) { Gdx.app.postRunnable {
            if (value != null) {
                if (_music != value) {
                    _music?.stop()
                    _music = value
                    _music?.volume = volumeLevelFlow.value / 100f
                    _music?.play()
                }
            } else {
                _music?.stop()
                _music = null
            }
        } }

    init {
        coroutine.launch { volumeLevelFlow.collect { level -> Gdx.app.postRunnable { _music?.volume = level / 100f } } }
    }

    override fun dispose() {
        coroutine.cancel()
        _music = null
        music  = null
    }

}