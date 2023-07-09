package com.veldan.base.box2d.game.utils.asset

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.veldan.base.box2d.game.manager.AudioManager
import com.veldan.base.box2d.game.utils.runGDX
import com.veldan.base.box2d.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MusicUtil: Disposable {

    private val coroutine = CoroutineScope(Dispatchers.IO)

//    val MAIN by lazy { MusicManager.EnumMusic.MAIN.data.music }
//    val MINI_GAME  get() = MusicManager.EnumMusic.MINI_GAME.data.music
//    val SUPER_GAME get() = MusicManager.EnumMusic.SUPER_GAME.data.music

//    val musicList by lazy { listOf(MAIN/*MINI_GAME, SUPER_GAME*/) }

    val volumeLevelFlow = MutableStateFlow(AudioManager.volumeLevelPercent)

    private var _music: Music? = null
    var music: Music?
        get() = _music
        set(value) { runGDX {
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
        coroutine.launch { volumeLevelFlow.collect { level -> _music?.volume = level / 100f } }
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutine)
        _music = null
        music  = null
    }

}