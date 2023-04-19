package com.veldan.junglego.assets.util

import com.badlogic.gdx.audio.Sound

data class SoundData(
    val path: String,
) {
    lateinit var sound: Sound
}