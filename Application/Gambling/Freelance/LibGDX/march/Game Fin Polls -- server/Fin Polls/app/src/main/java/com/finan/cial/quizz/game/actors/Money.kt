package com.finan.cial.quizz.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.finan.cial.quizz.game.manager.SpriteManager
import com.finan.cial.quizz.game.utils.advanced.AdvancedGroup

class Money: AdvancedGroup() {

    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.MON.region))
        addActor(Image(SpriteManager.GameRegion.MON.region).apply { setBounds(0f, this@Money.height, this@Money.width, this@Money.height) })
    }


    fun animmm() {
        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -height, 5f),
            Actions.moveBy(0f, height),
        )))
    }

}