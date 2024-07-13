package com.my.cooking.chef.kitchen.craze.fe.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.my.cooking.chef.kitchen.craze.fe.game.LibGDXGame
import com.my.cooking.chef.kitchen.craze.fe.game.utils.TIME_ANIM
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animHide
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animShow
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.setOnClickListener
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedStage
import com.my.cooking.chef.kitchen.craze.fe.game.utils.region

class Dilegatsia(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loal.listB.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.allOl.diloger)
        addActor(dialogImg)
        dialogImg.setBounds(0f, 157f, 541f, 803f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(361f, 396f, 164f, 42f)
        disagreeActor.setBounds(360f, 335f, 166f, 43f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("OSTAP", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("OSTAP", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

        val deva = Image(game.allOl.deva)
        val frtu = Image(game.allOl.fruitcandy)
        addActors(deva, frtu)
        deva.apply {
            setBounds(-24f, -46f, 269f, 433f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(15f, 30f, 3f, Interpolation.swingOut),
                Actions.moveBy(-15f, -30f, 3f, Interpolation.swing),
            )))
        }
        frtu.apply {
            setBounds(270f, -47f, 344f, 321f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(0f, 30f, 2f, Interpolation.swingOut),
                Actions.moveBy(0f, -30f, 2f, Interpolation.swing),
            )))
        }

    }

}