package plinko.originalwin.com.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen
import plinko.originalwin.com.game.utils.font.FontParameter

class AText(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(45)
    private val font          = screen.fontGenerator_ZenDots_Regular.generateFont(fontParameter)

    private val tmpGroup = TmpGroup(screen)
    private val textLbl  = Label("", Label.LabelStyle(font, Color.WHITE))


    override fun addActorsOnGroup() {
        addAndFillActor(tmpGroup)
        addTextLbl()
        tmpGroup.addAction(Actions.forever(Actions.sequence(
            Actions.alpha(0.3f, 0.4f),
            Actions.fadeIn(0.4f),
        )))
    }

    private fun addTextLbl() {
        tmpGroup.addAndFillActor(textLbl)
        textLbl.setAlignment(Align.center)
    }

    fun setText(text: String) {
        textLbl.setText(text)
    }

}