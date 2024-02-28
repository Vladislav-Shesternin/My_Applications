package com.balstar.linecomedian.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.balstar.linecomedian.game.LibGDXGame
import com.balstar.linecomedian.game.actors.checkbox.ACheckBox
import com.balstar.linecomedian.game.utils.TIME_ANIM
import com.balstar.linecomedian.game.utils.actor.animHide
import com.balstar.linecomedian.game.utils.actor.animShow
import com.balstar.linecomedian.game.utils.advanced.AdvancedScreen
import com.balstar.linecomedian.game.utils.advanced.AdvancedStage
import com.balstar.linecomedian.game.utils.region

class PinkSettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val panelImg = Image(game.allAssets.settings)
    private val musicCB  = ACheckBox(this, ACheckBox.Static.Type.CHECK)
    private val soundCB  = ACheckBox(this, ACheckBox.Static.Type.CHECK)

    override fun show() {
        stageUI.root.animHide(TIME_ANIM)
        setBackBackground(game.startAssets._3.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addMusicCB()
        addSoundCB()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActors(panelImg)
        panelImg.setBounds(118f, 344f, 845f, 1362f)
    }

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(576f, 1138f, 248f, 111f)
            if (game.musicUtil.music?.isPlaying == false) check(false)

            setOnCheckListener { isCheck ->
                if (isCheck) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }

        }
    }

    private fun AdvancedStage.addSoundCB() {
        addActor(soundCB)
        soundCB.apply {
            setBounds(576f, 783f, 248f, 111f)
            if (game.soundUtil.isPause) check(false)

            setOnCheckListener { isCheck -> game.soundUtil.isPause = isCheck }
        }
    }

}