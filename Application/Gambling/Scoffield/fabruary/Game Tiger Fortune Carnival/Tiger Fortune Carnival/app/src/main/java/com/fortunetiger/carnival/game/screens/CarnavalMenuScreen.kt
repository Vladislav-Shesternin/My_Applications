package com.fortunetiger.carnival.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.carnival.game.LibGDXGame
import com.fortunetiger.carnival.game.actors.checkbox.ACheckBox
import com.fortunetiger.carnival.game.utils.TIME_ANIM
import com.fortunetiger.carnival.game.utils.actor.animHide
import com.fortunetiger.carnival.game.utils.actor.animShow
import com.fortunetiger.carnival.game.utils.actor.setOnClickListener
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedStage
import com.fortunetiger.carnival.game.utils.region

private var counter = 0

class CarnavalMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        counter++
        if (counter % 7 == 0) game.activity.apply { adInterstitial.showAd(this) }

        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.CARNAVAL.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        val menuBar = Image(game.allAssets.Mentino)
        addActor(menuBar)
        menuBar.setBounds(76f, 1217f, 929f, 347f)

        val names = listOf(
            "exit",
            listOf(CarnavalGame3Screen::class.java.name, CarnavalGame4Screen::class.java.name).random(),
            CarnavalMusicScreen::class.java.name,
        )

        var nx = 161f

        names.onEach { sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(nx, 1280f, 233f, 233f)
            nx += 43f + 233f

            btn.setOnClickListener(game.soundUtil) { navigateGo(sName) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(sName: String) {
        stageUI.root.animHide(TIME_ANIM) {
            if (sName=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(sName, CarnavalMenuScreen::class.java.name)
        }
    }


}