package com.veldan.fantasticslots.screens.menu

import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.advanced.AdvancedScreen
import com.veldan.fantasticslots.advanced.AdvancedStage
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.assets.util.MusicUtil
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY

class MenuScreen : AdvancedScreen() {
    override val viewport   = FitViewport(WIDTH, HEIGHT)
    override val controller = MenuScreenController(this)

    private val playButton   = ButtonClickable(ButtonClickable.Style.button_1)
    private val optionButton = ButtonClickable(ButtonClickable.Style.button_1)
    private val exitButton   = ButtonClickable(ButtonClickable.Style.button_1)
    private val playLabel    = RollingLabel(activityResources.getString(R.string.play)   , LabelStyleUtil.robotoMono60)
    private val optionsLabel = RollingLabel(activityResources.getString(R.string.options), LabelStyleUtil.robotoMono60)
    private val exitLabel    = RollingLabel(activityResources.getString(R.string.exit)   , LabelStyleUtil.robotoMono60)



    override fun show() {
        super.show()
        with(MusicUtil) { currentMusic = MAIN }
        background = SpriteManager.MenuSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPlay()
        addOptions()
        addExit()
    }



    private fun AdvancedStage.addPlay() {
        addActor(playButton)
        playButton.apply {
            setBoundsFigmaY(Menu.PLAY_X, Menu.PLAY_Y, Menu.PLAY_W, Menu.PLAY_H)
            setOnClickListener { controller.playHandler() }
            addAndFillActor(playLabel)
        }
    }

    private fun AdvancedStage.addOptions() {
        addActor(optionButton)
        optionButton.apply {
            setBoundsFigmaY(Menu.OPTIONS_X, Menu.OPTIONS_Y, Menu.OPTIONS_W, Menu.OPTIONS_H)
            setOnClickListener { controller.optionsHandler() }
            addAndFillActor(optionsLabel)
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(exitButton)
        exitButton.apply {
            setBoundsFigmaY(Menu.EXIT_X, Menu.EXIT_Y, Menu.EXIT_W, Menu.EXIT_H)
            setOnClickListener { controller.exitHandler() }
            addAndFillActor(exitLabel)
        }
    }


}