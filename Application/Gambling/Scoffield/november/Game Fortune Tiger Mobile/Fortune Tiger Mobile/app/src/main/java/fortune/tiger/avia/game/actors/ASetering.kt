package fortune.tiger.avia.game.actors

import fortune.tiger.avia.game.actors.checkbox.ACheckBox
import fortune.tiger.avia.game.utils.advanced.AdvancedGroup
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen

private var isMusic = true
private var isSound = true

class ASetering(override val screen: AdvancedScreen): AdvancedGroup() {

    private val musicCB = ACheckBox(screen, ACheckBox.Static.Type.ON_OFF)
    private val soundCB = ACheckBox(screen, ACheckBox.Static.Type.ON_OFF)

    override fun addActorsOnGroup() {
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(0f, 167f, 248f, 111f)
            if (isMusic) uncheck(false) else check(false)
            setOnCheckListener {
                isMusic = it.not()
                screen.game.musicUtil.music?.apply { if (isMusic) play() else pause()  }
            }
        }
        soundCB.apply {
            setBounds(0f, 0f, 248f, 111f)
            if (isSound) uncheck(false) else check(false)
            setOnCheckListener {
                isSound = it.not()
                screen.game.soundUtil.isPause = isSound.not()
            }
        }
    }

}