package com.bigag.farm.garden.village.offline.farming.game.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bigag.farm.garden.village.offline.farming.game.game.LibGDXGame
import com.bigag.farm.garden.village.offline.farming.game.game.utils.TIME_ANIM
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animHide
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.setOnClickListener
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedScreen
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedStage
import com.bigag.farm.garden.village.offline.farming.game.game.utils.region

class Dali(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.splash.Splash.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.assets.dali)
        addActor(dialogImg)
        dialogImg.setBounds(68f, 59f, 927f, 1739f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(256f, 251f, 550f, 135f)
        disagreeActor.setBounds(256f, 59f, 550f, 135f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("vasa", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("vasa", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}