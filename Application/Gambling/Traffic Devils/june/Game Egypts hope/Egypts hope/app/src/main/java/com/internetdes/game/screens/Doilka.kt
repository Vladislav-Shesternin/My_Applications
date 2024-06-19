package com.internetdes.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.internetdes.game.LibGDXGame
import com.internetdes.game.utils.TIME_ANIM
import com.internetdes.game.utils.actor.animHide
import com.internetdes.game.utils.actor.setOnClickListener
import com.internetdes.game.utils.advanced.AdvancedScreen
import com.internetdes.game.utils.advanced.AdvancedStage
import com.internetdes.game.utils.region

class Doilka(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.aLOA.BAGRATION.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.aALL.doilka)
        addActor(dialogImg)
        dialogImg.setBounds(0f, 0f, 1527f, 887f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(622f, 30f, 365f, 138f)
        disagreeActor.setBounds(1007f, 30f, 365f, 138f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("doilka", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("doilka", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}