package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.actors.AButton
import com.centurygames.idlecouri.game.actors.checkbox.ACheckBox
import com.centurygames.idlecouri.game.utils.TIME_ANIM
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.actor.setOnClickListener
import com.centurygames.idlecouri.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.alpha.eeee)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(38f, 255f, 552f, 798f) })
        addPanel()

        addActor(back)
        back.apply {
            setBounds(156f, 82f, 315f, 92f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.ONOF)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.ONOF)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(377f, 584f, 166f, 162f)
            if (game.musicUtil.music?.isPlaying == false) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
        soundCB.apply {
            setBounds(377f, 367f, 166f, 162f)
            if (game.soundUtil.isPause) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
        }
    }

}