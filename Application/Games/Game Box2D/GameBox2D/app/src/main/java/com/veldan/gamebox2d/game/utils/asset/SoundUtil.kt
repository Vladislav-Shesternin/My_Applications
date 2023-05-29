//package com.vbbb.time.game.game.utils.asset
//
//import com.badlogic.gdx.audio.Sound
//import com.vbbb.time.game.game.manager.AudioManager
//import com.vbbb.time.game.game.utils.runGDX
//
//class SoundUtil {
//
//    //    val CLICK get() = SoundManager.EnumSound.CLICK.data.sound
////    val STAR  get() = SoundManager.EnumSound.STAR.data.sound
////    val STONE get() = SoundManager.EnumSound.STONE.data.sound
////
////    val soundList = listOf(CLICK, STAR, STONE)
////
//    var volumeLevel = AudioManager.volumeLevelPercent / 100f
//
//    var isPause = (volumeLevel <= 0f)
//
//    private var previousSoundId = 0L
//
//    fun play(sound: Sound) = runGDX {
//        if (isPause.not()) {
//            if (previousSoundId != 0L) sound.stop(previousSoundId)
//            previousSoundId = sound.play(volumeLevel)
//        }
//    }
//}