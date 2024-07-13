package com.amayasoft.cars.kids.toddlers.garage.ga.game.actors

import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedGroup
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align

class ACircle(override val screen: AdvancedScreen, reg: TextureRegion): AdvancedGroup() {

    private val image = Image(reg)

    override fun addActorsOnGroup() {
        addAndFillActor(image)
        image.setOrigin(Align.center)
        image.addAction(Actions.forever(Actions.rotateBy(-360f, 5f)))
    }

}