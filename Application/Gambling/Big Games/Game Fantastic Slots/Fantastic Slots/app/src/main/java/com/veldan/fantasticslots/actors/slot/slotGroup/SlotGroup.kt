package com.veldan.fantasticslots.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.fantasticslots.actors.mask.Mask
import com.veldan.fantasticslots.actors.slot.glow.Glow
import com.veldan.fantasticslots.actors.slot.slot.Slot
import com.veldan.fantasticslots.actors.slot.SpinResult
import com.veldan.fantasticslots.actors.slot.slotGroup.SlotGroupController.Companion.GLOW_COUNT
import com.veldan.fantasticslots.actors.slot.slotGroup.SlotGroupController.Companion.SLOT_COUNT
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.layout.setPositionFigmaY
import kotlinx.coroutines.coroutineScope
import com.veldan.fantasticslots.utils.Slot as S
import com.veldan.fantasticslots.utils.Glow as G
import com.veldan.fantasticslots.utils.SlotGroup as SG

class SlotGroup : AbstractAdvancedGroup() {
    override val controller = SlotGroupController(this)

    val slotList           = List(SLOT_COUNT) { Slot() }
    val glowList           = List(GLOW_COUNT) { Glow() }
    val maskGroup          = Mask(SpriteManager.GameSprite.SLOT_GROUP_MASK.data.texture)
    val panelImage         = Image(SpriteManager.GameSprite.SLOT_PANEL.data.texture)
    val separatorImage     = Image(SpriteManager.GameSprite.SEPARATOR.data.texture)
    val panelTutorialImage = Image(SpriteManager.GameSprite.SLOT_PANEL_TUTORIAL.data.texture)
    val glowTutorialImage  = Image(SpriteManager.GameSprite.GLOW_TUTORIAL.data.texture)


    init {
        setSize(SG.W, SG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addPanel()
        addGlowTutorial()
        addSeparator()
        addGlows()
        addMaskGroup()
        addPanelTutorial()
    }

    private fun addPanel() {
        addAndFillActor(panelImage)
    }

    private fun addGlowTutorial() {
        glowTutorialImage.addAction(Actions.alpha(0f))
        addAndFillActor(glowTutorialImage)
    }

    private fun addPanelTutorial() {
        panelTutorialImage.addAction(Actions.alpha(0f))
        addAndFillActor(panelTutorialImage)
    }

    private fun addSeparator() {
        addActor(separatorImage)
        separatorImage.setBoundsFigmaY(SG.SEPARATOR_X, SG.SEPARATOR_Y, SG.SEPARATOR_W, SG.SEPARATOR_H, SG.H)
    }

    private fun addGlows() {
        addActors(glowList)

        var newX = SG.GLOW_FIRS_X
        glowList.onEach {
            it.setPositionFigmaY(newX, SG.GLOW_Y, G.H, SG.H)
            newX += G.W + SG.GLOW_SPACE_HORIZONTAL
        }
    }

    private fun addMaskGroup() {
        addActor(maskGroup)
        maskGroup.setBoundsFigmaY(SG.MASK_GROUP_X, SG.MASK_GROUP_Y, SG.MASK_GROUP_W, SG.MASK_GROUP_H, SG.H)

        addSlots()
    }

    private fun addSlots() {
        maskGroup.addActors(slotList)

        var newX = SG.SLOT_FIRS_X
        slotList.onEach {
            it.setPositionFigmaY(newX, S.START_Y, S.H, SG.MASK_GROUP_H)
            newX += S.W + SG.SLOT_SPACE_HORIZONTAL
        }
    }


    suspend fun spin() = coroutineScope<SpinResult> {
       controller.spin()
    }

    fun showPanelTutorial() {
        controller.showTutorialPanel()
    }

    fun showGlowTutorial() {
        controller.showGlowTutorial()
    }

    fun hideTutorial() {
        controller.hideTutorial()
    }
    
}