package com.supertest.card.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.supertest.card.game.manager.SpriteManager
import com.supertest.card.game.utils.Deck
import com.supertest.card.game.utils.advanced.AdvancedGroup

class ACard(val card: Deck.Card): AdvancedGroup() {

    private val imageBack = Image(SpriteManager.GameRegion.BACK.region)
    private val imageCard = Image(card.region)

    private val time = 0.3f


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            imageBack.addAction(Actions.alpha(0f))
            addAndFillActors(imageBack, imageCard)
        }
    }


    fun hide() {
        imageCard.addAction(Actions.fadeOut(time))
        imageBack.addAction(Actions.fadeIn(time))
    }

    fun show() {
        imageBack.addAction(Actions.fadeOut(time))
        imageCard.addAction(Actions.fadeIn(time))
    }

}