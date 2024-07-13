package com.mvgamesteam.mineta.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.utils.TIME_ANIM
import com.mvgamesteam.mineta.game.utils.actor.animHide
import com.mvgamesteam.mineta.game.utils.actor.setOnClickListener
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedStage
import com.mvgamesteam.mineta.game.utils.region

class Diasaga(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.Sap.Splash.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.Jer.diasaga)
        addActor(dialogImg)
        dialogImg.setBounds(99f, 43f, 1324f, 768f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(851f, 43f, 433f, 144f)
        disagreeActor.setBounds(297f, 43f, 482f, 144f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(BOBIKRINA, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(BOBIKRINA, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}

val BOBIKRINA = "vasabi"