package com.centurygames.idlecourie.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecourie.game.HelloMotoGame
import com.centurygames.idlecourie.game.actors.button.AButton
import com.centurygames.idlecourie.game.actors.checkbox.ACheckBox
import com.centurygames.idlecourie.game.utils.Timek
import com.centurygames.idlecourie.game.utils.actor.animHide
import com.centurygames.idlecourie.game.utils.actor.animShow
import com.centurygames.idlecourie.game.utils.actor.setBounds
import com.centurygames.idlecourie.game.utils.actor.setOnClickListener
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecourie.game.utils.advanced.AdvancedStage
import com.centurygames.idlecourie.game.utils.region

class Settings_BUBA_Screen(override val game: HelloMotoGame): AdvancedScreen() {

    // Actor
    private val soundBox = ACheckBox(this, ACheckBox.Static.Type.CHEKEBOKSE)
    private val musicBox = ACheckBox(this, ACheckBox.Static.Type.CHEKEBOKSE)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.gudomidron.farmer.region)
        super.show()
        stageUI.root.animShow(Timek)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val back = AButton(this@Settings_BUBA_Screen, AButton.Static.Type.back)
        addActor(back)
        back.apply {
            setBounds(874f, 1044f, 128f, 128f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(Image(game.valhalla.piricessssa).apply { setBounds(136f, 0f, 809f, 883f) })
        addActor(Image(game.valhalla.musa).apply { setBounds(235f, 1378f, 396f, 430f) })
        addCBALSA()




        var rills = AButton(this@Settings_BUBA_Screen, AButton.Static.Type.Rules)
        var exitt = AButton(this@Settings_BUBA_Screen, AButton.Static.Type.Exit)
        addActor(rills)
        addActor(exitt)
        rills.apply {
            setBounds(348f, 1124f, 385f, 126f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.navigate(Rules_fdgDHHDH_Screen::class.java.name, Settings_BUBA_Screen::class.java.name)
                }
            }
        }
        exitt.apply {
            setBounds(348f, 959f, 385f, 126f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.exit()
                }
            }
        }
    }

    private fun AdvancedStage.addCBALSA() {
        addActor(musicBox)
        addActor(soundBox)
        musicBox.apply {
            setBounds(717f, 1644f, 128f, 128f)

            if (game.musicUtil.music?.isPlaying == true) check(false)

            setOnCheckListener {
                if (it) game.musicUtil.music?.play()
                else game.musicUtil.music?.pause()
            }
        }
        soundBox.apply {
            setBounds(717f, 1414f, 128f, 128f)

            if (game.soundUtil.isPause.not()) check(false)

            setOnCheckListener {
                game.soundUtil.isPause = it.not()
            }
        }
    }

}