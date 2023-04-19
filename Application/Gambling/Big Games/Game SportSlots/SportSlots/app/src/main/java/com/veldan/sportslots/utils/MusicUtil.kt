package com.veldan.sportslots.utils

import com.veldan.sportslots.assets.MusicManager

object MusicUtil {
    val MAIN = MusicManager.EnumMusic.MAIN.musicData.music
    val BONUS_SPIN = MusicManager.EnumMusic.BONUS_SPIN.musicData.music

    var currentMusic = MAIN
}