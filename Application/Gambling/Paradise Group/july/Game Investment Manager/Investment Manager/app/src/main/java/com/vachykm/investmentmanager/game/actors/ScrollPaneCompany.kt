package com.vachykm.investmentmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.vachykm.investmentmanager.game.actors.scroll.VerticalGroup
 import com.vachykm.investmentmanager.game.manager.NavigationManager
 import com.vachykm.investmentmanager.game.manager.SpriteManager
 import com.vachykm.investmentmanager.game.screens.BuyokScreen
 import com.vachykm.investmentmanager.game.screens.HomeSapienceScreen
 import com.vachykm.investmentmanager.game.screens.logogoStatik
 import com.vachykm.investmentmanager.game.screens.namemeStatik
 import com.vachykm.investmentmanager.game.utils.actor.disable
 import com.vachykm.investmentmanager.game.utils.actor.setOnClickListener
 import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup

class ScrollPaneCompany: AdvancedGroup() {

    private val titles = listOf(
        "Apple",
        "Chevron Corp",
        "Adobe Systems",
        "Delta Air Lines Inc",
        "Advanced Micro",
        "Walt Disney Co",
        "Amazon.com Inc",
        "Electronic Arts",
        "Activision Blizzard",
        "eBay Inc",
        "General Motors",
        "Boeing Company",
        "Ford Motor",
        "Alibaba Group",
        "Meta Platforms",
        "Bank of America",
        "First Solar Inc",
        "Berkshire Hath",
        "General Electric",
        "Cisco Systems",
    )

    private val vg = VerticalGroup(28f)
    private val sp = ScrollPane(vg)

    private val listok = mutableListOf<ItemCompany>()

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            (0..19).shuffled().onEach { index ->
                val rg = SpriteManager.ListRegion.ITEMS.regionList[index]
                val tl = titles[index]
                vg.addActor(ItemCompany(rg, tl).apply {
                    listok.add(this)
                    setSize(606f, 125f)
                    setOnClickListener {
                        this@ScrollPaneCompany.disable()
                        logogoStatik = rg
                        namemeStatik = tl
                        NavigationManager.navigate(BuyokScreen(), HomeSapienceScreen())
                    }
                })
            }
        }
    }

    fun update() {
        listok.onEach { it.updateCousCuda() }
    }

}