package com.tmesfo.frtunes.game.screens.splash

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tmesfo.frtunes.game.actors.label.LabelStyle
import com.tmesfo.frtunes.game.advanced.AdvancedScreen
import com.tmesfo.frtunes.game.advanced.AdvancedStage
import com.tmesfo.frtunes.game.game
import com.tmesfo.frtunes.game.manager.assets.SpriteManager
import com.tmesfo.frtunes.game.utils.region
import com.tmesfo.frtunes.lottie
import com.tmesfo.frtunes.game.layout.Layout.Splash as LS

class LoadorScreen : AdvancedScreen() {
    override val controller = LoadorScreenController(this)

    val progressLabel by lazy { Label("0", LabelStyle.abrilFatface_white_80) }



    override fun show() {
        super.show()
        lottie.showLoader()
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