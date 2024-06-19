package com.bandagames.mpuzzle.g.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.utils.TIME_ANIM
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.actor.setOnClickListener
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.region

class SkoroLifeBudeGood(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.Aoll.begi.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.Aoll.SKORO_CIA_HUETA_ZAKONCHITSA_I_BUDE_BOGATA_LIFE)
        addActor(dialogImg)
        dialogImg.setBounds(14f, 476f, 1051f, 1305f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(161f, 476f, 334f, 191f)
        disagreeActor.setBounds(584f, 476f, 334f, 191f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("biba", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("biba", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}