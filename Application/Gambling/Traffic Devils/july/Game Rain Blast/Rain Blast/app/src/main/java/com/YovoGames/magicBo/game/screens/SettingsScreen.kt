package com.YovoGames.magicBo.game.screens

import com.YovoGames.magicBo.game.LibGDXGame
import com.YovoGames.magicBo.game.actors.AButton
import com.YovoGames.magicBo.game.actors.checkbox.ACheckBox
import com.YovoGames.magicBo.game.utils.TIME_ANIM
import com.YovoGames.magicBo.game.utils.WIDTH_UI
import com.YovoGames.magicBo.game.utils.actor.animHide
import com.YovoGames.magicBo.game.utils.actor.animShow
import com.YovoGames.magicBo.game.utils.actor.setOnClickListener
import com.YovoGames.magicBo.game.utils.advanced.AdvancedScreen
import com.YovoGames.magicBo.game.utils.advanced.AdvancedStage
import com.YovoGames.magicBo.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.jakarta.somuska)
    private val back     = Actor()

    override fun show() {
        stageUI.root.x = -WIDTH_UI
        setBackBackground(game.surgut.Soloha.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(60f, 215f, 420f, 530f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(159f, 247f, 222f, 68f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.On_Off)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.On_Off)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(300f, 569f, 130f, 56f)
            if (game.musicUtil.music?.isPlaying == false) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
        soundCB.apply {
            setBounds(300f, 436f, 130f, 56f)
            if (game.soundUtil.isPause) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
        }
    }

}