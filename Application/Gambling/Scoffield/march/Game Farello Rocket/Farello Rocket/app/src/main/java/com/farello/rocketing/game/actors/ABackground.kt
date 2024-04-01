package com.farello.rocketing.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.farello.rocketing.game.utils.advanced.AdvancedGroup
import com.farello.rocketing.game.utils.advanced.AdvancedScreen

class ABackground constructor(override val screen: AdvancedScreen): AdvancedGroup() {

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.loaderAssets.FARELLO))
        addAndFillActor(Image(screen.game.loaderAssets.FARELLO).also { it.y = height })

        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -height, 5f),
            Actions.moveBy(0f, height),
        )))
    }

}