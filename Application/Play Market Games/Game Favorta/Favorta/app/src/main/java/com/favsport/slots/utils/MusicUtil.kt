package com.favsport.slots.utils

import com.favsport.slots.assets.MusicManager

object MusicUtil {
    val MAIN = MusicManager.EnumMusic.MAIN.musicData.music
    val BONUS_SPIN = MusicManager.EnumMusic.BONUS_SPIN.musicData.music

    var currentMusic = MAIN
}