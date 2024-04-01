package com.beeand.honey.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.beeand.honey.game.utils.advanced.AdvancedGroup
import com.beeand.honey.game.utils.advanced.AdvancedScreen

class ABackground(override val screen: AdvancedScreen): AdvancedGroup() {

    // Actor
    private val image1 = Image(screen.game.allAssets.BLUE)
    private val image2 = Image(screen.game.allAssets.BLUE)

    override fun addActorsOnGroup() {
        addAndFillActor(image1)
        addActor(image2)
        image2.setBounds(width, 0f, width, height)

        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(-width, 0f, 7f),
            Actions.moveBy(width, 0f),
        )))
    }

}