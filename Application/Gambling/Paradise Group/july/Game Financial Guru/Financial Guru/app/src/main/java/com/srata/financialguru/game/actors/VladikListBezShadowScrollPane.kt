package com.srata.financialguru.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.srata.financialguru.game.actors.scroll.VerticalGroup
 import com.srata.financialguru.game.manager.NavigationManager
 import com.srata.financialguru.game.manager.SpriteManager
 import com.srata.financialguru.game.screens.AnalizScreen
 import com.srata.financialguru.game.screens.VasiliyScreen
 import com.srata.financialguru.game.screens.carencyList
 import com.srata.financialguru.game.screens.regS
 import com.srata.financialguru.game.screens.ttlS
 import com.srata.financialguru.game.utils.actor.disable
 import com.srata.financialguru.game.utils.actor.setOnClickListener
 import com.srata.financialguru.game.utils.advanced.AdvancedGroup

class VladikListBezShadowScrollPane: AdvancedGroup() {

    private val vg = VerticalGroup(18f)
    private val sp = ScrollPane(vg)

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            (0..9).shuffled().onEach { index ->
                val rg = SpriteManager.ListRegion.ITEMS.regionList[index]
                val tl = carencyList[index]
                vg.addActor(VladikListBezShadowItem(rg, tl).apply {
                    setSize(626f, 141f)
                    setOnClickListener {
                        this@VladikListBezShadowScrollPane.disable()
                        regS = rg
                        ttlS = tl
                        NavigationManager.navigate(AnalizScreen(), VasiliyScreen())
                    }
                })
            }
        }
    }

}