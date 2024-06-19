package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.Block
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.animHide
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.setBounds
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.setOnClickListener
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.region

class DialogScreen(override val game: GDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.assetsLoader.background_purple.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.assetsAll.dialog)
        addActor(dialogImg)
        dialogImg.setBounds(185f, 44f, 1235f, 843f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(481f, 44f, 257f, 140f)
        disagreeActor.setBounds(868f, 44f, 257f, 140f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("answer", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("answer", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}