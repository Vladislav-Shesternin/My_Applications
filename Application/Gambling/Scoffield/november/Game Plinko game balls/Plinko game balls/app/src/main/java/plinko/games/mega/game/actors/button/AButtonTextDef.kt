package plinko.games.mega.game.actors.button

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import plinko.games.mega.game.utils.actor.disable
import plinko.games.mega.game.utils.advanced.AdvancedScreen

class AButtonTextDef(
    override val screen: AdvancedScreen,
    val text      : String,
    val labelStyle: LabelStyle,
): AButton(screen, Static.Type.DEFAULT) {

    private val label = Label(text, labelStyle)

    override fun addActorsOnGroup() {
        super.addActorsOnGroup()
        addLabel()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addLabel() {
        addActor(label)
        label.also {
            it.setBounds(24f, 19f, 471f, 113f)
            it.disable()
            it.setAlignment(Align.center)
            it.wrap = true
        }
    }

}