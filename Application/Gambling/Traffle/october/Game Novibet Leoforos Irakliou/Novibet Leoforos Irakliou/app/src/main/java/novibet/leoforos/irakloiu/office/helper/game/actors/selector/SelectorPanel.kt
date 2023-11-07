package novibet.leoforos.irakloiu.office.helper.game.actors.selector

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.flow.MutableStateFlow
import novibet.leoforos.irakloiu.office.helper.game.actors.scroll.VerticalGroup
import novibet.leoforos.irakloiu.office.helper.game.utils.GameColor
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnTouchListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedGroup
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter

class SelectorPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.LATIN).setSize(33)
    private val font33    = screen.fontGeneratorLondrinaSolid_Regular.generateFont(parameter)

    private val verticalGroup = VerticalGroup(screen, 35f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val scrollPanel   = ScrollPane(verticalGroup)

    // Field
    private val professions = listOf(
        "sales manager",
        "designer",
        "developer",
        "technical engineer",
        "HR manager",
        "administrator",
        "lawyer",
        "interface designer",
        "technical leader"
    )

    val professionFlow = MutableStateFlow(professions.first())
    var selectBlock    = {}

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.SELECTOR_PANEL))
        addProfession()
    }

    private fun addProfession() {
        addActor(scrollPanel)
        scrollPanel.setBounds(29f, 15f, 474f, 495f)

        val ls = LabelStyle(font33, GameColor.text)

        professions.onEach { prof ->
            verticalGroup.addActor(Label(prof, ls).apply {
                setSize(437f, 40f)
                setAlignment(Align.center)

                setOnTouchListener {
                    professionFlow.value = prof
                    selectBlock()
                }
            })
        }
    }

}