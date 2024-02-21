package plinko.originalwin.com.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.originalwin.com.game.actors.checkbox.ACheckBox
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.setOnClickListener
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen

class ASettings(override val screen: AdvancedScreen): AdvancedGroup() {

    private val exitImg   = Image(screen.game.allAssets.button_exit)
    private val soundCBox = ACheckBox(screen, ACheckBox.Static.Type.SOUND)

    override fun addActorsOnGroup() {
        addExitImg()
        addSoundCBox()
    }

    private fun addExitImg() {
        addActor(exitImg)
        exitImg.apply {
            setSize(111f, 151f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide { screen.game.navigationManager.back() }
            }
        }
    }

    private fun addSoundCBox() {
        addActor(soundCBox)
        soundCBox.apply {
            setBounds(133f, 0f,111f, 151f)

            val soundUtil = screen.game.soundUtil

            if (soundUtil.isPause) check(false)

            setOnCheckListener { isCheck -> soundUtil.isPause = isCheck }
        }
    }

}