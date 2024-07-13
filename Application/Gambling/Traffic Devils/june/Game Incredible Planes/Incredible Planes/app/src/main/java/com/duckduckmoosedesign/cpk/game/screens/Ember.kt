package com.duckduckmoosedesign.cpk.game.screens

import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.setOnClickListener
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Ember(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.Mis.Firster.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        val dialogImg = Image(game.Ser.Ember)
        addActor(dialogImg)
        dialogImg.setBounds(46f, 121f, 1339f, 676f)


        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(1056f, 506f, 329f, 196f)
        disagreeActor.setBounds(1056f, 121f, 329f, 196f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(yyyataYSggs, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(yyyataYSggs, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}

val yyyataYSggs = "oIASldj"