package seville.vontecarlo.chocolatequiz.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import seville.vontecarlo.chocolatequiz.game.LibGDXGame
import seville.vontecarlo.chocolatequiz.game.actors.ACheckBox
import seville.vontecarlo.chocolatequiz.game.manager.SpriteManager
import seville.vontecarlo.chocolatequiz.game.utils.actor.animHide
import seville.vontecarlo.chocolatequiz.game.utils.actor.animShow
import seville.vontecarlo.chocolatequiz.game.utils.actor.setOnClickListener
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedScreen
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedStage
import seville.vontecarlo.chocolatequiz.game.utils.region

class WonkaMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val wonkaQuiz = Image(assets.quiz)
    private val wonkaExit = Image(assets.exit)
    private val musicCb   = ACheckBox(this, ACheckBox.Static.Type.Music)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(SpriteManager.EnumTexture.BACKGROUND.data.texture.region)
        super.show()
        stageUI.root.animShow(0.33f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addButtons()
        addMusic()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addButtons() {
        addActors(wonkaQuiz, wonkaExit)
        wonkaQuiz.apply {
            setBounds(65f, 791f, 671f, 144f)
            setOnClickListener { navigacia(WonkaQuizScreen::class.java.name) }
        }
        wonkaExit.apply {
            setBounds(65f, 603f, 671f, 144f)
            setOnClickListener { navigacia("wonkaExit") }
        }
    }

    private fun AdvancedStage.addMusic() {
        addActor(musicCb)
        musicCb.apply {
            setBounds(237f, 175f, 348f, 348f)
            if (game.musicUtil.music!!.isPlaying.not()) musicCb.check(false)
            setOnCheckListener { if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play() }
        }
    }

    private fun navigacia(nameString: String) {
        stageUI.root.animHide(0.33f) {
            if (nameString=="wonkaExit") game.navigationManager.exit()
            else game.navigationManager.navigate(nameString, this::class.java.name)
        }
    }

}