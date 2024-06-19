package com.maxgames.stickwarl.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.maxgames.stickwarl.game.utils.advanced.AdvancedGroup
import com.maxgames.stickwarl.game.utils.advanced.AdvancedScreen

class AKoleso(override val screen: AdvancedScreen): AdvancedGroup() {

    private val image = Image(screen.game.assets.kolo)

    override fun addActorsOnGroup() {
        addAndFillActor(image)
        image.apply {
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(-360f, 5f)))
        }
    }

}