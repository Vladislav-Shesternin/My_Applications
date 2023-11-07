package mst.mysteryof.egyptest.game.actors.button

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import mst.mysteryof.egyptest.game.utils.actor.disable
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedScreen

class AButtonText(
    override val screen: AdvancedScreen,
    text: String, type: Type? = null, labelStyle: Label.LabelStyle, val alignment: Int = Align.center
): AButton(screen, type) {

    private val label = Label(text, labelStyle)

    override fun addActorsOnGroup() {
        super.addActorsOnGroup()
        addLabel()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addLabel() {
        addAndFillActor(label)
        label.disable()
        label.setAlignment(alignment)
        label.wrap = true
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun setText(text: String) {
        label.setText(text)
    }

}