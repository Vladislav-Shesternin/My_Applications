package com.veldan.bigwinslots777.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.bigwinslots777.actors.label.LabelStyle
import com.veldan.bigwinslots777.advanced.AdvancedScreen
import com.veldan.bigwinslots777.advanced.AdvancedStage
import com.veldan.bigwinslots777.main.game
import com.veldan.bigwinslots777.manager.assets.SpriteManager
import com.veldan.bigwinslots777.utils.region
import com.veldan.bigwinslots777.layout.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val controller = SplashScreenController(this)

    val progressLabel by lazy { Label("0", LabelStyle.gold_200) }



    override fun show() {
        super.show()
        game.activity.controller.showLoader()
        controller.loadSplashAssets()
        setBackgrounds(SpriteManager.SourceTexture.BACKGROUND.data.texture.region)
        stage.addActorsOnStage()
        controller.loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        controller.loadingAssets()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgress()
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setAlignment(Align.center)
            setBounds(LS.PROGRESS_X, LS.PROGRESS_Y, LS.PROGRESS_W, LS.PROGRESS_H)
        }
    }


}