//package notconvert.notvalue.notvista.game.manager.util
//
//import com.badlogic.gdx.audio.Sound
//import notconvert.notvalue.notvista.game.manager.AudioManager
//import notconvert.notvalue.notvista.game.manager.SoundManager
//import notconvert.notvalue.notvista.game.utils.runGDX
//import notconvert.notvalue.notvista.util.log
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