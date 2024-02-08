package com.tmesfo.frtunes.game.screens.menu

import com.tmesfo.frtunes.R
import com.tmesfo.frtunes.game.actors.button.ButtonClickable
import com.tmesfo.frtunes.game.actors.button.ButtonClickableStyle
import com.tmesfo.frtunes.game.actors.label.LabelStyle
import com.tmesfo.frtunes.game.actors.label.spinning.SpinningLabel
import com.tmesfo.frtunes.game.advanced.AdvancedScreen
import com.tmesfo.frtunes.game.advanced.AdvancedStage
import com.tmesfo.frtunes.game.manager.NavigationManager
import com.tmesfo.frtunes.game.manager.assets.SpriteManager
import com.tmesfo.frtunes.game.manager.assets.util.MusicUtil
import com.tmesfo.frtunes.game.screens.game.GameScreen
import com.tmesfo.frtunes.game.utils.language.Language
import com.tmesfo.frtunes.game.utils.region
import com.tmesfo.frtunes.game.layout.Layout.Menu as LM

class MenuScreen : AdvancedScreen() {
    override val controller = MenuScreenController(this)

    val playButton        = ButtonClickable(ButtonClickableStyle.button_1)
    val playTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.play), LabelStyle.font_black2_80) }
    val exitButton        = ButtonClickable(ButtonClickableStyle.button_1)
    val exitTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.exit), LabelStyle.font_black2_80) }



    override fun show() {
        super.show()
        with(MusicUtil) { currentMusic = MAIN }
        setBackgrounds(SpriteManager.SourceTexture.BACKGROUND.data.texture.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPlayButton()
        addExitButton()
    }

    private fun AdvancedStage.addPlayButton() {
        addActor(playButton)
        playButton.apply {
            setBounds(LM.PLAY_X, LM.PLAY_Y, LM.PLAY_W, LM.PLAY_H)

            addActor(playTextLabel)
            playTextLabel.setBounds(LM.PLAY_TEXT_X, LM.PLAY_TEXT_Y, LM.PLAY_TEXT_W, LM.PLAY_TEXT_H)

            controller.setOnClickListener { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        }
    }

    private fun AdvancedStage.addExitButton() {
        addActor(exitButton)
        exitButton.apply {
            setBounds(LM.EXIT_X, LM.EXIT_Y, LM.EXIT_W, LM.EXIT_H)

            addActor(exitTextLabel)
            exitTextLabel.setBounds(LM.EXIT_TEXT_X, LM.EXIT_TEXT_Y, LM.EXIT_TEXT_W, LM.EXIT_TEXT_H)

            controller.setOnClickListener { NavigationManager.exit() }
        }
    }


}