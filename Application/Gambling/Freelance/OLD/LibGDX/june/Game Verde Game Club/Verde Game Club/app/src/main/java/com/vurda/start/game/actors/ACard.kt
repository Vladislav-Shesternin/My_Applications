package com.vurda.start.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.vurda.start.game.manager.SpriteManager
import com.vurda.start.game.utils.Deck
import com.vurda.start.game.utils.advanced.AdvancedGroup

class ACard(val card: Deck.Card): AdvancedGroup() {

    private val imageBack = Image(SpriteManager.GameRegion.BACK.region)
    private val imageCard = Image(card.region)

    private val time = 0.2f


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