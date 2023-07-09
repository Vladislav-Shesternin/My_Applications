package com.bango.weld.androit.game.utils.asset

import com.bango.weld.androit.game.utils.runGDX
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.bango.weld.androit.game.manager.MusicManager

class MusicUtil: Disposable {

    val MAIN by lazy { MusicManager.EnumMusic.MAIN.data.music }
//    val MINI_GAME  get() = MusicManager.EnumMusic.MINI_GAME.data.music
//    val SUPER_GAME get() = MusicManager.EnumMusic.SUPER_GAME.data.music

//    val musicList by lazy { listOf(MAIN/*MINI_GAME, SUPER_GAME*/) }

    private var _music: Music? = null
    var music: Music?
        get() = _music
        set(value) { runGDX {
            if (value != null) {
                if (_music != value) {
                    _music?.stop()
                    _music = value
                    _music?.play()
                }
            } else {
                _music?.stop()
                _music = null
            }
        } }


    override fun dispose() {
        _music = null
        music  = null
    }

}