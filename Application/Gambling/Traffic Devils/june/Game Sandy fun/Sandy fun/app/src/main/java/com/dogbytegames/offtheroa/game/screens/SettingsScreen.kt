package com.dogbytegames.offtheroa.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dogbytegames.offtheroa.game.LibGDXGame
import com.dogbytegames.offtheroa.game.actors.AButton
import com.dogbytegames.offtheroa.game.actors.checkbox.ACheckBox
import com.dogbytegames.offtheroa.game.utils.TIME_ANIM
import com.dogbytegames.offtheroa.game.utils.actor.animHide
import com.dogbytegames.offtheroa.game.utils.actor.animShow
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedScreen
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedStage
import com.dogbytegames.offtheroa.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val icon       = Image(game.aAlibaba.nubasina)
    private val settings   = Image(game.aAlibaba.musatina)
    private val back       = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLdnr.LDR.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(0f, 0f, 638f, 656f) })
        addActor(settings.apply { setBounds(507f, 229f, 856f, 569f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(915f, 50f, 433f, 142f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.DEFAULT)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.DEFAULT)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(717f, 412f, 202f, 202f)
            if (game.musicUtil.music?.isPlaying == true) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.play() else game.musicUtil.music?.pause()
            }
        }
        soundCB.apply {
            setBounds(1053f, 412f, 202f, 202f)
            if (game.soundUtil.isPause.not()) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it.not()
            }
        }
    }

}