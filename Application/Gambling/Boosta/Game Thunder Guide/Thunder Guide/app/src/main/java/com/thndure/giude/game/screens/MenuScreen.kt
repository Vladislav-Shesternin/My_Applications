package com.thndure.giude.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.thndure.giude.game.LibGDXGame
import com.thndure.giude.game.actors.button.AButton
import com.thndure.giude.game.actors.button.AButtonText
import com.thndure.giude.game.utils.GameColor
import com.thndure.giude.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.thndure.giude.game.utils.actor.animHide
import com.thndure.giude.game.utils.actor.animShow
import com.thndure.giude.game.utils.advanced.AdvancedScreen
import com.thndure.giude.game.utils.advanced.AdvancedStage
import com.thndure.giude.game.utils.font.FontParameter
import com.thndure.giude.game.utils.region

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parameter = FontParameter().setCharacters("PlayExit").setSize(50)
    private val font      = fontGeneratorArial_Black.generateFont(parameter)

    // Actor
    private val btnPlay = AButtonText(this, "Play", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))
    private val btnExit = AButtonText(this, "Exit", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPlay()
        addExit()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPlay() {
        addActor(btnPlay)
        btnPlay.apply {
            setBounds(472f, 412f, 477f, 101f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(GameScreen::class.java.name, this::class.java.name) } }
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(btnExit)
        btnExit.apply {
            setBounds(472f, 257f, 477f, 101f)
            setOnClickListener { game.navigationManager.exit() }
        }
    }

}