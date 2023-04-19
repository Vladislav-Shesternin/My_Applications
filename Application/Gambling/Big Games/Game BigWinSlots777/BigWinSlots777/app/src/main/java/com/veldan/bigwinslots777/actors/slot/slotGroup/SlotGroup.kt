package com.veldan.bigwinslots777.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.bigwinslots777.actors.masks.normal.Mask
import com.veldan.bigwinslots777.actors.miniGameGroup.MiniGameGroup
import com.veldan.bigwinslots777.actors.slot.glow.Glow
import com.veldan.bigwinslots777.actors.slot.slot.Slot
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup
import com.veldan.bigwinslots777.manager.assets.SpriteManager
import com.veldan.bigwinslots777.utils.listeners.toClickable
import com.veldan.bigwinslots777.layout.Layout.Glow as LG
import com.veldan.bigwinslots777.layout.Layout.Slot as LS
import com.veldan.bigwinslots777.layout.Layout.SlotGroup as LSG

class SlotGroup : AbstractAdvancedGroup() {
    override val controller = SlotGroupController(this)

    val slotList = List(SlotGroupController.SLOT_COUNT) { Slot() }
    val glowList = List(SlotGroupController.GLOW_COUNT) { Glow() }
    val mask = Mask()
    val panelImage = Image(SpriteManager.GameRegion.SLOT_GROUP_PANEL.region)

    lateinit var miniGameGroup: MiniGameGroup



    init {
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

    // ------------------------------------------------------------------------
    // MiniGameGroup
    // ------------------------------------------------------------------------
    fun addMiniGameGroup() {
        miniGameGroup = MiniGameGroup(this)
        addActor(miniGameGroup)
        miniGameGroup.setPosition(LSG.MINI_GAME_X, LSG.MINI_GAME_Y)
    }

}