package com.amayasoft.cars.kids.toddlers.garage.ga.game.screens

import com.amayasoft.cars.kids.toddlers.garage.ga.game.LibGDXGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.TIME_ANIM
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animHide
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.setOnClickListener
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedStage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.region
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Derulo(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        setBackBackground(game.S3.Splash.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {




        val fruits = Image(game.G1.XCBVVBN)
        addActor(fruits)
        fruits.apply {
            setBounds(-3f, -42f, 769f, 230f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                        Actions.moveBy(0f, 39f, TIME_ANIM, Interpolation.sine),
                        Actions.moveBy(0f, -39f, TIME_ANIM, Interpolation.sine),
                    )))
        }

        val dialogImg = Image(game.G1.frodo)
        addActor(dialogImg)
        dialogImg.setBounds(0f, 72f, 764f, 378f)


        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(468f, 72f, 183f, 82f)
        disagreeActor.setBounds(267f, 73f, 183f, 82f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(fiesta, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean(fiesta, true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}

val fiesta = "fiechka"