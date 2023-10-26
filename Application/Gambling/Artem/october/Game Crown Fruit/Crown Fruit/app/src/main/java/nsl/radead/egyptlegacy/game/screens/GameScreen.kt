package nsl.radead.egyptlegacy.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.launch
import nsl.radead.egyptlegacy.game.LibGDXGame
import nsl.radead.egyptlegacy.game.actors.button.AButton
import nsl.radead.egyptlegacy.game.actors.slots.slot5x3.SlotGroup
import nsl.radead.egyptlegacy.game.utils.TIME_ANIM_SCREEN_ALPHA
import nsl.radead.egyptlegacy.game.utils.actor.animHide
import nsl.radead.egyptlegacy.game.utils.actor.animShow
import nsl.radead.egyptlegacy.game.utils.actor.setOnClickListener
import nsl.radead.egyptlegacy.game.utils.advanced.AdvancedScreen
import nsl.radead.egyptlegacy.game.utils.advanced.AdvancedStage
import nsl.radead.egyptlegacy.game.utils.region
import nsl.radead.egyptlegacy.game.utils.runGDX

class GameScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val btnGo     = AButton(this, AButton.Type.GO)
    private val btnMenu   = AButton(this, AButton.Type.MENU)
    private val slotGroup = SlotGroup(this)
    private val imgPanel  = Image(game.spriteUtil.PANEL)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addGo()
        addMenu()
        addSlotGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActor(imgPanel)
        imgPanel.setBounds(45f, 70f, 1105f, 684f)
    }

    private fun AdvancedStage.addGo() {
        addActor(btnGo)
        btnGo.apply {
            setBounds(1182f, 311f, 203f, 203f)
            setOnClickListener { spinHandler() }
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(btnMenu)
        btnMenu.apply {
            setBounds(1220f, 171f, 126f, 43f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setBounds(52f, 77f, 1091f, 670f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun AButton.spinHandler() {
        disable()

        coroutine?.launch {
            slotGroup.spin()
            runGDX { enable() }
        }
    }

}