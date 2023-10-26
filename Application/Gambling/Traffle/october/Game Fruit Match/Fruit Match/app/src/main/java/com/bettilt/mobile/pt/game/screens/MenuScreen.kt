package com.bettilt.mobile.pt.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.actors.button.AButton
import com.bettilt.mobile.pt.game.actors.button.AButtonText
import com.bettilt.mobile.pt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.animShow
import com.bettilt.mobile.pt.game.utils.actor.setOnClickListener
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedMouseScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedStage
import com.bettilt.mobile.pt.game.utils.font.FontParameter
import com.bettilt.mobile.pt.game.utils.region
import com.bettilt.mobile.pt.util.Once

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        private val oncePlayMusic = Once()
    }

    private val parameterBtn = FontParameter().setCharacters("GameSettingsExit").setSize(59)
    private val font59       = fontGeneratorKadwa_Bold.generateFont(parameterBtn)

    private val girlImg     = Image(game.spriteUtil.GIRL)
    private val gameBtn     = AButtonText(this, "Game", AButton.Type.DEFAULT, Label.LabelStyle(font59, Color.BLACK))
    private val settingsBtn = AButtonText(this, "Settings", AButton.Type.DEFAULT, Label.LabelStyle(font59, Color.BLACK))
    private val exitBtn     = AButtonText(this, "Exit", AButton.Type.DEFAULT, Label.LabelStyle(font59, Color.BLACK))

    override fun show() {
        oncePlayMusic.once { game.musicUtil.apply {
            cof   = 20f
            music = MUSIC
        } }

        stageUI.root.animHide()
        setBackgrounds(game.spriteUtil.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addGirlImg()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addGirlImg() {
        addActor(girlImg)
        girlImg.apply {
            setBounds(-316f, 0f, 316f, 482f)
            setOrigin(Align.center)
            animGirl()
        }
    }

    private fun AdvancedStage.addBtns() {
        addActors(gameBtn, settingsBtn, exitBtn)
        gameBtn.apply {
            setBounds(835f, 507f, 596f, 148f)
            setOnClickListener { navigateTo(GameScreen::class.java.name) }
        }
        settingsBtn.apply {
            setBounds(835f, 325f, 596f, 148f)
            setOnClickListener { navigateTo(SettingsScreen::class.java.name) }
        }
        exitBtn.apply {
            setBounds(835f, 143f, 596f, 148f)
            setOnClickListener { game.navigationManager.exit() }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private fun animGirl() {
        girlImg.addAction(Actions.forever(Actions.sequence(
            Actions.moveTo(495f, 0f, 4f, Interpolation.sine),
            Actions.moveTo(49f, 0f, 3f, Interpolation.sine),
        )))
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun navigateTo(name: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(name, MenuScreen::class.java.name) }
    }

}