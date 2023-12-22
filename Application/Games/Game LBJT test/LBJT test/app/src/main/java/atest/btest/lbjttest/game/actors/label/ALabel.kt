package atest.btest.lbjttest.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import atest.btest.lbjttest.game.utils.advanced.AdvancedGroup
import atest.btest.lbjttest.game.utils.advanced.AdvancedScreen

class ALabel constructor(
    override val screen: AdvancedScreen,
    text      : CharSequence,
    labelStyle: LabelStyle,
): AdvancedGroup() {

    val label = Label(text, labelStyle)


    override fun addActorsOnGroup() {
        addAndFillActor(label)
    }

}