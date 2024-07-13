package com.bitmango.go.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bitmango.go.game.LibGDXGame
import com.bitmango.go.game.utils.TIME_ANIM
import com.bitmango.go.game.utils.actor.animHide
import com.bitmango.go.game.utils.actor.animShow
import com.bitmango.go.game.utils.actor.setOnClickListener
import com.bitmango.go.game.utils.advanced.AdvancedScreen
import com.bitmango.go.game.utils.advanced.AdvancedStage
import com.bitmango.go.game.utils.region

class JorgScreen(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aaa.back.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.bbb.Jorg)
        addActor(dialogImg)
        dialogImg.setBounds(30f, 90f, 479f, 734f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(310f, 90f, 188f, 78f)
        disagreeActor.setBounds(42f, 90f, 188f, 78f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("IZmaIl", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("IZmaIl", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}