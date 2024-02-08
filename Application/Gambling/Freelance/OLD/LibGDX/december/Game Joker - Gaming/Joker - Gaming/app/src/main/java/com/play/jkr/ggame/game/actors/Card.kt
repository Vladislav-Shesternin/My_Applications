package com.play.jkr.ggame.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.play.jkr.ggame.game.actors.image.AImage
import com.play.jkr.ggame.game.manager.SpriteManager
import com.play.jkr.ggame.game.util.advanced.AdvancedGroup

class Card: AdvancedGroup() {

    val card  = AImage()
    val image = AImage()



    override fun sizeChanged() {
        super.sizeChanged()

        if (width > 0 && height > 0) {
            addActor(image)
            addAndFillActor(card)

            image.apply {
                addAction(Actions.alpha(0f))
                setBounds(0f, 23f, 107f, 107f)
            }
        }
    }



    fun open(block: () -> Unit = {}) {
        card.addAction(Actions.fadeOut(0.4f))
        image.addAction(Actions.sequence(
            Actions.fadeIn(0.4f),
            Actions.run { block() }
        ))
    }

    fun close(block: () -> Unit = {}) {
        card.addAction(Actions.fadeIn(0.4f))
        image.addAction(Actions.sequence(
            Actions.fadeOut(0.4f),
            Actions.run { block() }
        ))
    }

}