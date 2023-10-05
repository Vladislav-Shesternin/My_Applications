//package crupp.tsk.learnn.inggg.game.manager.util
//
//import com.badlogic.gdx.audio.Sound
//import crupp.tsk.learnn.inggg.game.manager.AudioManager
//import crupp.tsk.learnn.inggg.game.manager.SoundManager
//import crupp.tsk.learnn.inggg.game.utils.runGDX
//import crupp.tsk.learnn.inggg.util.log
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