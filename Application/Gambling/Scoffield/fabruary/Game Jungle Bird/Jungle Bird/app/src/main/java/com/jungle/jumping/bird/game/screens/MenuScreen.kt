package com.jungle.jumping.bird.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jungle.jumping.bird.game.actors.button.AButton
import com.jungle.jumping.bird.game.actors.button.AButtonStyle
import com.jungle.jumping.bird.game.actors.image.AImage
import com.jungle.jumping.bird.game.actors.label.ALabelStyle
import com.jungle.jumping.bird.game.manager.NavigationManager
import com.jungle.jumping.bird.game.manager.SpriteManager
import com.jungle.jumping.bird.game.utils.Layout
import com.jungle.jumping.bird.game.utils.actor.setBounds
import com.jungle.jumping.bird.game.utils.advanced.AdvancedScreen
import com.jungle.jumping.bird.game.utils.advanced.AdvancedStage
import com.jungle.jumping.bird.game.utils.runGDX
import kotlinx.coroutines.launch
import com.jungle.jumping.bird.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {

    private val playButton  = AButton(AButtonStyle.play)
    private val recordLabel = Label("", ALabelStyle.font_45)



    override fun show() {
        super.show()
        setBackBackground(SpriteManager.GameRegion.BACKGROUND.region)
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
            setBounds(Layout.Splash.button)
            setOnClickListener {
                swing?.play(1f)
                NavigationManager.navigate(GameScreen(), MenuScreen())
            }
        }
    }

    private fun AdvancedStage.addRecord() {
        val panel = AImage(com.jungle.jumping.bird.game.manager.SpriteManager.GameRegion.PANEL.region)
        addActor(panel)
        panel.apply {
            setBounds(LM.record)
            addAndFillActor(recordLabel)
            recordLabel.apply {
                setAlignment(Align.center)
                coroutine.launch {
                    val record = com.jungle.jumping.bird.game.manager.GameDataStoreManager.Record.get() ?: 0
                    runGDX { setText(record) }
                }
            }
        }
    }

}