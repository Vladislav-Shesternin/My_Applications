package com.veldan.bigwinslots777.actors.super_game.elementGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.veldan.bigwinslots777.actors.label.LabelStyle
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup
import com.veldan.bigwinslots777.advanced.group.AdvancedGroup
import com.veldan.bigwinslots777.manager.assets.SpriteManager
import com.veldan.bigwinslots777.layout.Layout.SuperGameElementGroup as LSGE

class SuperGameElementGroup(
    val numberList: List<Int>? = null,
): AbstractAdvancedGroup() {
    override val controller = SuperGameElementGroupController(this)

    private val elementRegionList = listOf(
        SpriteManager.SuperGameRegion.SPIN_PANEL.region,
        SpriteManager.SuperGameRegion.COEFFICIENT_PANEL.region,
        SpriteManager.SuperGameRegion.WILD_PANEL.region,
    )

    val elementImageList = List(3) { Image() }
    val elementLabelList = List(3) { Label(numberList?.get(it)?.toString() ?: "" , LabelStyle.gold_70).apply { setAlignment(Align.center) } }



    init {
        setSize(LSGE.W, LSGE.H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addElementList()
    }

    private fun AdvancedGroup.addElementList() {
        var newPanelX = LSGE.PANEL_FIRST_X
        var newLabelX = LSGE.LABEL_FIRST_X

        var panel: Image
        var label: Label

        elementRegionList.onEachIndexed { index, region ->
            panel = elementImageList[index].apply {
                drawable = TextureRegionDrawable(region)
                setBounds(newPanelX, LSGE.PANEL_Y, LSGE.PANEL_W, LSGE.PANEL_H)
                newPanelX += LSGE.PANEL_W + LSGE.PANEL_SPACE_HORIZONTAL
            }
            label = elementLabelList[index].apply {
                setBounds(newLabelX, LSGE.LABEL_Y, LSGE.LABEL_W, LSGE.LABEL_H)
                newLabelX += LSGE.LABEL_W + LSGE.LABEL_SPACE_HORIZONTAL
            }
            addActors(panel, label)
        }
    }

}