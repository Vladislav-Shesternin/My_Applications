package com.favsport.slots.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.favsport.slots.*
import com.favsport.slots.actors.ButtonClickable
import com.favsport.slots.advanced.AdvancedScreen
import com.favsport.slots.advanced.AdvancedStage
import com.favsport.slots.assets.MusicManager
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.utils.*
import java.util.*

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
        playMusic()
        stage.addActorsOnStage()
    }

    private fun AdvancedStage.addActorsOnStage(){
        addButtonPanel()
        addPlay()
        addSettings()
        addExit()
    }



    private fun AdvancedStage.addButtonPanel(){
        val image = Image(SpriteManager.MenuSprite.BUTTON_PANEL.textureData.texture).apply {
            setBoundsFigmaY(BUTTON_PANEL_X, BUTTON_PANEL_Y, BUTTON_PANEL_W, BUTTON_PANEL_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addPlay(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = languageSprite.play_def,
            pressed = languageSprite.play_press,
        )).apply { 
            setBoundsFigmaY(PLAY_X, PLAY_Y, PLAY_W, PLAY_H)
            setOnClickListener(SoundUtil.CLICK) { playHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addSettings(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = languageSprite.settings_def,
            pressed = languageSprite.settings_press,
        )).apply {
            setBoundsFigmaY(SETTINGS_X, SETTINGS_Y, SETTINGS_W, SETTINGS_H)
            setOnClickListener(SoundUtil.CLICK) { settingsHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addExit() {
        val button = ButtonClickable(ButtonClickable.Style(
            default = languageSprite.exit_def,
            pressed = languageSprite.exit_press,
        )).apply {
            setBoundsFigmaY(EXIT_X, EXIT_Y, EXIT_W, EXIT_H)
            setOnClickListener { exitHandler() }
        }
        addActor(button)
    }



    private fun ButtonClickable.playHandler() {
        NavigationUtil.navigate(GameScreen(), MenuScreen())
    }

    private fun ButtonClickable.settingsHandler() {
        NavigationUtil.navigate(SettingsScreen(), MenuScreen())
    }

    private fun ButtonClickable.exitHandler() {
        NavigationUtil.exit()
    }



    private fun playMusic(){
        MusicUtil.currentMusic.apply {
            isLooping = true
            volume = MUSIC_VOLUME
            play()
        }
    }




    
}