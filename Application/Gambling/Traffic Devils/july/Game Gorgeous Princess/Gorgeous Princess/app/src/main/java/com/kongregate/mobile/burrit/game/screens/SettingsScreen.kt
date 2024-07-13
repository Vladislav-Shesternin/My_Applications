package com.kongregate.mobile.burrit.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.kongregate.mobile.burrit.game.LibGDXGame
import com.kongregate.mobile.burrit.game.actors.AButton
import com.kongregate.mobile.burrit.game.actors.checkbox.ACheckBox
import com.kongregate.mobile.burrit.game.utils.TIME_ANIM
import com.kongregate.mobile.burrit.game.utils.actor.animHide
import com.kongregate.mobile.burrit.game.utils.actor.animShow
import com.kongregate.mobile.burrit.game.utils.actor.setOnClickListener
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedScreen
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedStage
import com.kongregate.mobile.burrit.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.alpha.cklekeis)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.bacMini.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(37f, 453f, 1030f, 1421f) })
        addPanel()

        addActor(back)
        back.apply {
            setBounds(348f, 96f, 384f, 155f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.MUS)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.SOD)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(254f, 1100f, 573f, 231f)
            if (game.musicUtil.music?.isPlaying == false) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
        soundCB.apply {
            setBounds(254f, 725f, 573f, 231f)
            if (game.soundUtil.isPause) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
        }
    }

}