package com.veldan.junglego.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.junglego.HEIGHT
import com.veldan.junglego.WIDTH
import com.veldan.junglego.actors.ButtonClickable
import com.veldan.junglego.advanced.AdvancedGroup.AlignmentHorizontal as H
import com.veldan.junglego.advanced.AdvancedGroup.AlignmentVertical as V
import com.veldan.junglego.advanced.AdvancedScreen
import com.veldan.junglego.advanced.AdvancedStage
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.MusicUtil
import com.veldan.junglego.languageSprite
import com.veldan.junglego.utils.Menu
import com.veldan.junglego.manager.NavigationManager
import com.veldan.junglego.utils.setBoundsFigmaY

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
        MusicUtil.playMainMusic()
        background = SpriteManager.MenuSprite.BACKGROUND.textureData.texture
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPlay()
        addOptions()
        addExit()
    }


    private fun AdvancedStage.addPlay() {
        val button = ButtonClickable(ButtonClickable.Style.style_1).apply {
            setBoundsFigmaY(Menu.PLAY_X, Menu.PLAY_Y, Menu.PLAY_W, Menu.PLAY_H)

            val image = Image(languageSprite.PLAY).apply { setSize(Menu.PLAY_TEXT_W, Menu.PLAY_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener { playHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addOptions() {
        val button = ButtonClickable(ButtonClickable.Style.style_1).apply {
            setBoundsFigmaY(Menu.OPTIONS_X, Menu.OPTIONS_Y, Menu.OPTIONS_W, Menu.OPTIONS_H)

            val image = Image(languageSprite.OPTIONS).apply { setSize(Menu.OPTIONS_TEXT_W, Menu.OPTIONS_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener { optionsHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addExit() {
        val button = ButtonClickable(ButtonClickable.Style.style_1).apply {
            setBoundsFigmaY(Menu.EXIT_X, Menu.EXIT_Y, Menu.EXIT_W, Menu.EXIT_H)

            val image = Image(languageSprite.EXIT).apply { setSize(Menu.EXIT_TEXT_W, Menu.EXIT_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener(null) { exitHandler() }
        }
        addActor(button)
    }
    
    
    
    private fun ButtonClickable.playHandler() {
        NavigationManager.navigate(GameScreen(), MenuScreen())
    }

    private fun ButtonClickable.optionsHandler() {
        NavigationManager.navigate(OptionsScreen(), MenuScreen())
    }

    private fun ButtonClickable.exitHandler() {
        NavigationManager.exit()
    }



    private fun MusicUtil.playMainMusic() {
        currentMusic = MAIN.apply { isLooping = true }
    }


}