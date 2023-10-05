//package mange.yourse.finnnance.game.manager.util
//
//import com.badlogic.gdx.audio.Sound
//import mange.yourse.finnnance.game.manager.AudioManager
//import mange.yourse.finnnance.game.manager.SoundManager
//import mange.yourse.finnnance.game.utils.runGDX
//import mange.yourse.finnnance.util.log
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