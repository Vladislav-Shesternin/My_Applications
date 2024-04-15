package qbl.bisriymyach.QuickBall.tidams

import com.badlogic.gdx.audio.Sound
import qbl.bisriymyach.QuickBall.fastergan.gugols
import qbl.bisriymyach.QuickBall.fastergan.runGDX

class mim {

    val bum     = sosisochki_na_grili.EnumSound.bum.data.sound
    val clk     = sosisochki_na_grili.EnumSound.clk.data.sound
    val funil   = sosisochki_na_grili.EnumSound.funil.data.sound
    val podrank = sosisochki_na_grili.EnumSound.podrank.data.sound

    var volumeLevel = gugols.vodelfinkaumeLelP

    var isPause = (volumeLevel <= 0f)

    fun play(sound: Sound, volume: Float = volumeLevel) = runGDX {
        if (isPause.not()) sound.play(volume / 100f)
    }
}

