package com.fortunetiger.carnival.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.carnival.game.LibGDXGame
import com.fortunetiger.carnival.game.actors.checkbox.ACheckBox
import com.fortunetiger.carnival.game.utils.TIME_ANIM
import com.fortunetiger.carnival.game.utils.actor.animHide
import com.fortunetiger.carnival.game.utils.actor.animShow
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedStage
import com.fortunetiger.carnival.game.utils.region

class CarnavalMusicScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.CARNAVAL.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addSound()
        addMusic()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        val menuBar = Image(game.allAssets.Music)
        addActor(menuBar)
        menuBar.setBounds(76f, 1139f, 929f, 545f)
    }

    private fun AdvancedStage.addSound() {
        val sound = ACheckBox(this@CarnavalMusicScreen, ACheckBox.Static.Type.SOUND)
        addActor(sound)
        sound.setBounds(227f, 1286f, 167f, 167f)

        if (game.soundUtil.isPause) sound.check(false)

        sound.setOnCheckListener { game.soundUtil.isPause = it }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@CarnavalMusicScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(661f, 1286f, 167f, 167f)

        if (game.musicUtil.music?.isPlaying == false) music.check(false)

        music.setOnCheckListener { isCheck ->
            if (isCheck) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
        }
    }

}