package com.veldan.fantasticslots.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.fantasticslots.AndroidLauncher
import com.veldan.fantasticslots.advanced.AdvancedScreen
import com.veldan.fantasticslots.advanced.AdvancedStage
import com.veldan.fantasticslots.assetManager
import com.veldan.fantasticslots.assets.*
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.screens.menu.MenuScreen
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val viewport   = FitViewport(WIDTH, HEIGHT)
    override val controller = SplashScreenController(this)

    val progressLabel by lazy { Label("0", Label.LabelStyle(FontBMPManager.EnumFont.LUCIDA_110.data.font, null)) }



    override fun show() {
        super.show()
        AndroidLauncher.showLoader()
        controller.loadSplashAssets()
        background = SpriteManager.SplashSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
        controller.loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        if (assetManager.update()) {
            controller.onceLoadAssets.once {
                with(controller) {
                    showProgress(1f)
                    initAssets()
                }
                AndroidLauncher.hideLoader()
                NavigationManager.navigate(MenuScreen())
            }
        }
        else controller.showProgress(assetManager.progress)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgress()
    }


    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBoundsFigmaY(Splash.PROGRESS_X, Splash.PROGRESS_Y, Splash.PROGRESS_W, Splash.PROGRESS_H)
            setAlignment(Align.center)
        }
    }


}