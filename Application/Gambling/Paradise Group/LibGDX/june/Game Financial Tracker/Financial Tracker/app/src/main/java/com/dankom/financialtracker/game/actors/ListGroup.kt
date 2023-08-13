package com.dankom.financialtracker.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.dankom.financialtracker.game.actors.scroll.VerticalGroup
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup

class ListGroup: AdvancedGroup() {

    private val vG = VerticalGroup(48f, endGap = 0f)
    private val sp = ScrollPane(vG)

    private val listik = listOf("LuxCorp","AeroTech","NovaTech","PixelPro","EvoTech","VitalTech","SwiftX","BlazeTech","AquaTech","VibeTech","HyperCore","SynthPro","FusionX","OptiTech","QuantumQ","RadiX","ZephyrTech","VoltX","SolarX","AxonTech","VoxTech","SwiftPro","AeroX","ZenPro","AeroMax","NexusX","PulseTech", "VitaX")

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            val l = SpriteManager.ListRegion.LOGOS.regionList.shuffled()
            val t = listik.shuffled()

            repeat(28) { index ->
                vG.addActor(ItemListGroup(l[index], t[index]).apply { setSize(568f, 80f) })
            }
        }
    }

}