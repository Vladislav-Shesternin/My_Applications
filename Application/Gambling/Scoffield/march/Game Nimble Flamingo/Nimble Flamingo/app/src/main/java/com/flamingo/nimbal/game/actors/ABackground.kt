package com.flamingo.nimbal.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.flamingo.nimbal.game.utils.advanced.AdvancedGroup
import com.flamingo.nimbal.game.utils.advanced.AdvancedScreen

class ABackground(override val screen: AdvancedScreen): AdvancedGroup() {

    // Actor
    private val image1 = Image(screen.game.startAssets.BACKGROUND)
    private val image2 = Image(screen.game.startAssets.BACKGROUND)

    override fun addActorsOnGroup() {
        addAndFillActor(image1)
        addActor(image2)
        image2.setBounds(width, 0f, width, height)

        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(-width, 0f, 10f),
            Actions.moveBy(width, 0f),
        )))
    }

}