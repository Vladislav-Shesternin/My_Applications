package com.tmesfo.frtunes.game.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tmesfo.frtunes.game.actors.masks.normal.Mask
import com.tmesfo.frtunes.game.actors.slot.glow.Glow
import com.tmesfo.frtunes.game.actors.slot.slot.Slot
import com.tmesfo.frtunes.game.advanced.group.AbstractAdvancedGroup
import com.tmesfo.frtunes.game.manager.assets.SpriteManager
import com.tmesfo.frtunes.game.utils.disable
import com.tmesfo.frtunes.game.layout.Layout.Glow as LG
import com.tmesfo.frtunes.game.layout.Layout.Slot as LS
import com.tmesfo.frtunes.game.layout.Layout.SlotGroup as LSG

class SlotGroup : AbstractAdvancedGroup() {
    override val controller = SlotGroupController(this)

    val slotList   = List(SlotGroupController.SLOT_COUNT) { Slot() }
    val glowList   = List(SlotGroupController.GLOW_COUNT) { Glow() }
    val mask       = Mask(SpriteManager.GameRegion.SLOT_GROUP_MASK.region)
    val panelImage = Image(SpriteManager.GameRegion.SLOT_GROUP_PANEL.region)



    init {
        disable()
        setSize(LSG.W, LSG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addPanel()
        addGlows()
        addMask()
    }

    private fun addPanel() {
        addAndFillActor(panelImage)
    }

    private fun addGlows() {
        var newX = LSG.GLOW_FIRST_X
        glowList.onEach { glow ->
            addActor(glow)
            glow.setPosition(newX, LSG.GLOW_Y)
            newX += LG.W + LSG.GLOW_SPACE_HORIZONTAL
        }
    }

    private fun addMask() {
        addActor(mask)
        mask.setBounds(LSG.MASK_X, LSG.MASK_Y, LSG.MASK_W, LSG.MASK_H)

        addSlots()
    }

    private fun addSlots() {
        var newX = LSG.SLOT_FIRST_X
        slotList.onEach { slot ->
            mask.addActor(slot)
            slot.setPosition(newX, LS.START_Y)
            newX += LS.W + LSG.SLOT_SPACE_HORIZONTAL
        }
    }

}