package novibet.leoforos.irakloiu.office.helper.game.actors.selector

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import novibet.leoforos.irakloiu.office.helper.game.utils.GameColor
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animShow
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.disable
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.enable
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnClickListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedGroup
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter
import novibet.leoforos.irakloiu.office.helper.game.utils.runGDX

class SelectorGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.LATIN).setSize(33)
    private val font33    = screen.fontGeneratorLondrinaSolid_Regular.generateFont(parameter)

    private val regionDef   = screen.game.spriteUtil.SELECTOR
    private val regionPress = TextureRegion(screen.game.spriteUtil.SELECTOR).apply { flip(false, true) }

    // Actor
    private val selectorPanel = SelectorPanel(screen)
    private val background    = Image(regionDef)
    private val professionLbl = Label(selectorPanel.professionFlow.value, Label.LabelStyle(font33, GameColor.text))

    // Field
    private var isOpen = false
    var selectBlock    = {}

    override fun addActorsOnGroup() {
        addAndFillActor(background)
        addProfessionLbl()
        addSelectorPanel()

        clickHandler()
    }

    private fun addProfessionLbl() {
        addActor(professionLbl)
        professionLbl.setBounds(22f, 22f, 437f, 40f)
    }

    private fun addSelectorPanel() {
        addActor(selectorPanel)
        selectorPanel.apply {
            disable()
            animHide()
            setBounds(0f, -547f, 530f, 525f)

            selectBlock = { closeSelector() }
        }

        coroutine?.launch {
            selectorPanel.professionFlow.collect {
                runGDX { professionLbl.setText(it) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun clickHandler() {
        setOnClickListener { if (isOpen) closeSelector() else openSelector() }
    }

    private fun openSelector() {
        isOpen = true
        background.drawable = TextureRegionDrawable(regionPress)
        selectorPanel.apply {
            enable()
            animShow(0.3f)
        }
    }

    private fun closeSelector() {
        isOpen = false
        background.drawable = TextureRegionDrawable(regionDef)
        selectorPanel.apply {
            disable()
            animHide(0.25f)
        }
        selectBlock()
    }

}