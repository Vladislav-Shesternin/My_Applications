package com.kongregate.mobile.burrit.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.kongregate.mobile.burrit.game.LibGDXGame
import com.kongregate.mobile.burrit.game.utils.TIME_ANIM
import com.kongregate.mobile.burrit.game.utils.actor.animHide
import com.kongregate.mobile.burrit.game.utils.actor.animShow
import com.kongregate.mobile.burrit.game.utils.actor.setOnClickListener
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedScreen
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedStage
import com.kongregate.mobile.burrit.game.utils.region

class GeraltScreen(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.bacMini.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.alpha.DupaloG)
        addActor(dialogImg)
        dialogImg.setBounds(63f, 149f, 954f, 1635f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(587f, 149f, 384f, 155f)
        disagreeActor.setBounds(109f, 149f, 384f, 155f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("popol_papel", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("popol_papel", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}