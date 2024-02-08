package com.thndure.giude.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.launch
import com.thndure.giude.game.LibGDXGame
import com.thndure.giude.game.actors.button.AButton
import com.thndure.giude.game.actors.slots.slot5x3.SlotGroup
import com.thndure.giude.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.thndure.giude.game.utils.actor.animHide
import com.thndure.giude.game.utils.actor.animShow
import com.thndure.giude.game.utils.advanced.AdvancedScreen
import com.thndure.giude.game.utils.advanced.AdvancedStage
import com.thndure.giude.game.utils.region
import com.thndure.giude.game.utils.runGDX

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