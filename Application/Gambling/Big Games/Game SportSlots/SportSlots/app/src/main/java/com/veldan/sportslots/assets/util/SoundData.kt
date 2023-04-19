package com.veldan.sportslots.assets.util

import com.badlogic.gdx.audio.Sound

data class SoundData(
    val path: String,
) {
    lateinit var sound: Sound
}