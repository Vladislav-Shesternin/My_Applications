package com.veldan.sportslots.assets.util

import com.badlogic.gdx.audio.Music

data class MusicData(
    val path: String,
) {
    lateinit var music: Music
}