package com.fellinger.yeasman.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.fellinger.yeasman.game.actors.button.AButton
import com.fellinger.yeasman.game.actors.button.AButtonStyle
import com.fellinger.yeasman.game.actors.label.ALabelStyle
import com.fellinger.yeasman.game.manager.SpriteManager
import com.fellinger.yeasman.game.utils.actor.setBounds
import com.fellinger.yeasman.game.utils.advanced.AdvancedScreen
import com.fellinger.yeasman.game.utils.advanced.AdvancedStage
import com.fellinger.yeasman.game.utils.runGDX
import kotlinx.coroutines.launch
import com.fellinger.yeasman.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {

    private val playButton  = AButton(AButtonStyle.play)
    private val recordLabel = Label("RECORD:", ALabelStyle.red_100)
    private val countLabel  = Label("0", ALabelStyle.black_150)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPlay()
        addRecord()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPlay() {
        addActor(playButton)
        playButton.apply {
            setBounds(LM.play)
            setOnClickListener { com.fellinger.yeasman.game.manager.NavigationManager.navigate(
                GameScreen(), MenuScreen()
            ) }
        }
    }

    private fun AdvancedStage.addRecord() {
        addActors(recordLabel, countLabel)
        recordLabel.apply {
            setBounds(LM.record)
            setAlignment(Align.center)
        }
        countLabel.apply {
            setBounds(LM.count)
            coroutine.launch {
                val record = com.fellinger.yeasman.game.manager.GameDataStoreManager.Record.get() ?: 0
                runGDX { setText(record) }
            }
        }
    }

}