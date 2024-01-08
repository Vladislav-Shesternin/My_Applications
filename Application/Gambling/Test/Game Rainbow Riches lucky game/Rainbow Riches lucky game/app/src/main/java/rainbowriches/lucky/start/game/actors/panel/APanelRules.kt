package rainbowriches.lucky.start.game.actors.panel

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.font.FontParameter

var rulesText: String? = null

class APanelRules(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private const val DEFAULT_RULES_TEXT = "1. In a certain time it is necessary to correctly assemble a whole picture from the pieces.\n" +
                "\n" +
                "2. The player can choose the level (light, medium, heavy).\n" +
                "\n" +
                "3. When the time has expired, but the picture is not assembled - lose\n" +
                "\n" +
                "4. The player can go through the levels indefinitely."
    }

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font_37   = screen.fontSemiBold.generateFont(parameter.setSize(38))

    private val panelImg  = APanelka(screen, APanelka.Static.Type.RULES)
    private val textLabel = Label(rulesText ?: DEFAULT_RULES_TEXT, Label.LabelStyle(font_37, Color.WHITE))


    override fun addActorsOnGroup() {
        addAndFillActor(panelImg)
        addLbl()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addLbl() {
        addActor(textLabel)
        textLabel.apply {
            setBounds(137f, 184f, 620f, 640f)
            wrap = true
        }
    }

}