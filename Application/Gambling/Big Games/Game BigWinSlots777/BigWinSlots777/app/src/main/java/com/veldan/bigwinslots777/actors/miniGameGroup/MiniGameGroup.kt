package com.veldan.bigwinslots777.actors.miniGameGroup

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.bigwinslots777.actors.slot.slotGroup.SlotGroup
import com.veldan.bigwinslots777.actors.slot.util.SlotItemContainer
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup
import com.veldan.bigwinslots777.advanced.group.AdvancedGroup
import com.veldan.bigwinslots777.utils.disable
import com.veldan.bigwinslots777.utils.enable
import com.veldan.bigwinslots777.utils.listeners.toClickable
import kotlinx.coroutines.flow.MutableStateFlow
import com.veldan.bigwinslots777.layout.Layout.MiniGameGroup as LMG

class MiniGameGroup(
    val slotGroup: SlotGroup
): AbstractAdvancedGroup() {
    override val controller = MiniGameGroupController(this)

    companion object {
        var slotIndex = -1
        var rowIndex = -1

        val isCheckWild = MutableStateFlow(false)
    }

    val itemLists = List(12) { Actor() }.chunked(3)


    init {
        debug()
        setSize(LMG.W, LMG.H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addItemList()
    }

    private fun AdvancedGroup.addItemList() {
        var newItemX = LMG.ITEM_FIRST_X
        itemLists.onEachIndexed { itemListIndex, itemList ->
            var newItemY = LMG.ITEM_FIRST_Y
            itemList.onEachIndexed { itemIndex, item ->
                item.debug()
                addActor(item)
                item.setBounds(newItemX, newItemY, LMG.ITEM_W, LMG.ITEM_H)
                newItemY -= LMG.ITEM_H + LMG.ITEM_SPACE_VERTICAL

                item.toClickable().setOnClickListener {
                    this@MiniGameGroup.disable()
                    slotIndex = itemListIndex
                    rowIndex  = itemIndex
                    slotGroup.slotList[slotIndex].slotItemImageFakeList[rowIndex].apply {
                        addAction(Actions.sequence(
                            Actions.fadeOut(1f),
                            Actions.run { drawable = TextureRegionDrawable(SlotItemContainer.wild.region) },
                            Actions.fadeIn(1f),
                            Actions.run {
                                isCheckWild.value = true
                                this@MiniGameGroup.remove()
                            }
                        ))
                    }
                }
            }
            newItemX += LMG.ITEM_W + LMG.ITEM_SPACE_HORIZONTAL
        }
    }

}