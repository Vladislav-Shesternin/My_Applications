//package golov.lomaka.sudjoken.game.manager.util
//
//import com.badlogic.gdx.audio.Sound
//import golov.lomaka.sudjoken.game.manager.AudioManager
//import golov.lomaka.sudjoken.game.manager.SoundManager
//import golov.lomaka.sudjoken.game.utils.runGDX
//import golov.lomaka.sudjoken.util.log
//
//class SoundUtil {
//
//    // Collision
//    val ITEM   = SoundManager.EnumSound.ITEM.data.sound
//    val AY = SoundManager.EnumSound.AY.data.sound
//
//    var volumeLevel = AudioManager.volumeLevelPercent / 100f
//
//    var isPause = (volumeLevel <= 0f)
//
//    private var previousSoundId = 0L
//
//    fun play(sound: Sound, isStopPrev: Boolean = false) = runGDX {
//        if (isPause.not()) {
//            if (isStopPrev && previousSoundId != 0L) sound.stop(previousSoundId)
//            previousSoundId = sound.play(0.3f)
//        }
//    }
//}