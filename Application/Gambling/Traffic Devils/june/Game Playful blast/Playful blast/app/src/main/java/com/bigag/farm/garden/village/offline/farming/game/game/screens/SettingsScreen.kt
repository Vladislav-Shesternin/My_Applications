package com.bigag.farm.garden.village.offline.farming.game.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bigag.farm.garden.village.offline.farming.game.game.LibGDXGame
import com.bigag.farm.garden.village.offline.farming.game.game.actors.AButton
import com.bigag.farm.garden.village.offline.farming.game.game.actors.checkbox.ACheckBox
import com.bigag.farm.garden.village.offline.farming.game.game.utils.TIME_ANIM
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animHide
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animShow
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedScreen
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedStage
import com.bigag.farm.garden.village.offline.farming.game.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings   = Image(game.assets.SAIOL)
    private val back       = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.splash.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(326f, 963f, 410f, 835f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(161f, 130f, 741f, 183f)
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
            setBounds(271f, 1145f, 521f, 521f)
            if (game.musicUtil.music?.isPlaying == true) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.play() else game.musicUtil.music?.pause()
            }
        }
        soundCB.apply {
            setBounds(271f, 424f, 521f, 521f)
            if (game.soundUtil.isPause.not()) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it.not()
            }
        }
    }

}