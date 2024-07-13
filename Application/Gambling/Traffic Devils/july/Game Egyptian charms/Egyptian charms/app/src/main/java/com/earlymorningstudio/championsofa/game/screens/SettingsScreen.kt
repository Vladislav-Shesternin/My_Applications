package com.earlymorningstudio.championsofa.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.earlymorningstudio.championsofa.game.LibGDXGame
import com.earlymorningstudio.championsofa.game.actors.AButton
import com.earlymorningstudio.championsofa.game.actors.checkbox.ACheckBox
import com.earlymorningstudio.championsofa.game.utils.TIME_ANIM
import com.earlymorningstudio.championsofa.game.utils.actor.animHide
import com.earlymorningstudio.championsofa.game.utils.actor.animShow
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedScreen
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedStage
import com.earlymorningstudio.championsofa.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val icon = Image(game.LICHIKO.uubm)
    private val back = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.SRAKA.PUNDIC.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(-28f, -5f, 788f, 611f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(1184f, 25f, 295f, 156f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.Musicm)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.Sound)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(628f, 517f, 342f, 263f)
            if (game.musicUtil.music?.isPlaying == false) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
        soundCB.apply {
            setBounds(1102f, 343f, 342f, 263f)
            if (game.soundUtil.isPause) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
        }
    }

}