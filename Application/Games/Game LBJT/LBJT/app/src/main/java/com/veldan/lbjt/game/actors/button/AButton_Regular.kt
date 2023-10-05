package com.veldan.lbjt.game.actors.button

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen

class AButton_Regular(
    override val screen: AdvancedScreen,
    text: String, labelStyle: Label.LabelStyle
): AButton(screen, Type.REGULAR) {

    override var standartW = 466f

    val label = Label(text, labelStyle)

    override fun addActorsOnGroup() {
        super.addActorsOnGroup()
        addLabel()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addLabel() {
        addActor(label)
        label.apply {
            setBoundsStandart(0f,19f,466f,150f)
            disable()
            setAlignment(Align.center)
            wrap = true
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun setText(text: String) {
        label.setText(text)
    }

}