package com.orange.magic.board.doodle.color.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.actors.button.AButton
import com.orange.magic.board.doodle.color.game.actors.checkbox.ACheckBox
import com.orange.magic.board.doodle.color.game.utils.TIMI_TERNER
import com.orange.magic.board.doodle.color.game.utils.actor.animHide
import com.orange.magic.board.doodle.color.game.utils.actor.animShow
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedScreen
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedStage
import com.orange.magic.board.doodle.color.game.utils.region

class MedsikScreen(override val game: LidaGame): AdvancedScreen() {

    // Actor
    private val soundBox = ACheckBox(this, ACheckBox.Static.Type.SamoGonichka)
    private val musicBox = ACheckBox(this, ACheckBox.Static.Type.SamoGonichka)

    private val aMenu = AButton(
        this,
        AButton.Static.Type.MNU
    )

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.dgop.meduna.region)
        super.show()
        stageUI.root.animShow(TIMI_TERNER)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()

        addActor(Image(game.simagoche.stronsiy).apply {
            setBounds(357f, 1317f, 365f, 361f)
        })
        addActor(Image(game.simagoche.misika_soudika).apply {
            setBounds(125f, 461f, 594f, 621f)
        })

        addBoxix()
    }

    // ------------------------------------------------------------------------
    // Create A
    // ----------------------------------------
    private fun AdvancedStage.addMenu() {
        addActors(aMenu)

        aMenu.apply {
            setBounds(34f, 1773f, 112f, 112f)
            setOnClickListener {
                stageUI.root.animHide(TIMI_TERNER) { game.navigationManager.back() }
            }
        }
    }

    private fun AdvancedStage.addBoxix() {
        addActors(soundBox, musicBox)


        soundBox.apply {
            setBounds(811f, 508f, 150f, 150f)

            if (game.soundUtil.isPause.not()) check(false)

            setOnCheckListener {
                game.soundUtil.isPause = it.not()
            }
        }

        musicBox.apply {
            setBounds(811f, 885f, 150f, 150f)

            if (game.musicUtil.music?.isPlaying == true) check(false)

            setOnCheckListener {
                if (it) game.musicUtil.music?.play()
                else game.musicUtil.music?.pause()
            }
        }
    }

}