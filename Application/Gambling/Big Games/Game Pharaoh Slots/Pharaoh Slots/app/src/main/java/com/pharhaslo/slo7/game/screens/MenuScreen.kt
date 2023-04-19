package com.pharhaslo.slo7.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.pharhaslo.slo7.game.utils.HEIGHT
import com.pharhaslo.slo7.game.utils.WIDTH
import com.pharhaslo.slo7.game.actors.ButtonClickable
import com.pharhaslo.slo7.game.advanced.AdvancedGroup.AlignmentHorizontal as H
import com.pharhaslo.slo7.game.advanced.AdvancedGroup.AlignmentVertical as V
import com.pharhaslo.slo7.game.advanced.AdvancedScreen
import com.pharhaslo.slo7.game.advanced.AdvancedStage
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.MusicUtil
import com.pharhaslo.slo7.game.languageSprite
import com.pharhaslo.slo7.game.utils.Menu
import com.pharhaslo.slo7.game.manager.NavigationManager
import com.pharhaslo.slo7.game.utils.setBoundsFigmaY

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    override fun show() {
        super.show()
        MusicUtil.playMainMusic()
        setBackground(SpriteManager.MenuSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPlay()
        addOptions()
        addExit()
    }


    private fun AdvancedStage.addPlay() {
        val button = ButtonClickable(ButtonClickable.Style.button_1).apply {
            setBoundsFigmaY(Menu.PLAY_X, Menu.PLAY_Y, Menu.PLAY_W, Menu.PLAY_H)

            val image = Image(languageSprite.PLAY).apply { setSize(Menu.BUTTON_TEXT_W, Menu.BUTTON_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener { playHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addOptions() {
        val button = ButtonClickable(ButtonClickable.Style.button_1).apply {
            setBoundsFigmaY(Menu.OPTIONS_X, Menu.OPTIONS_Y, Menu.OPTIONS_W, Menu.OPTIONS_H)

            val image = Image(languageSprite.OPTIONS).apply { setSize(Menu.BUTTON_TEXT_W, Menu.BUTTON_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener { optionsHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addExit() {
        val button = ButtonClickable(ButtonClickable.Style.button_1).apply {
            setBoundsFigmaY(Menu.EXIT_X, Menu.EXIT_Y, Menu.EXIT_W, Menu.EXIT_H)

            val image = Image(languageSprite.EXIT).apply { setSize(Menu.BUTTON_TEXT_W, Menu.BUTTON_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener(null) { exitHandler() }
        }
        addActor(button)
    }



    private fun playHandler() {
        NavigationManager.navigate(GameScreen(), MenuScreen())
    }

    private fun optionsHandler() {
        NavigationManager.navigate(OptionsScreen(), MenuScreen())
    }

    private fun exitHandler() {
        NavigationManager.exit()
    }



    private fun MusicUtil.playMainMusic() {
        currentMusic = MAIN.apply { isLooping = true }
    }


}