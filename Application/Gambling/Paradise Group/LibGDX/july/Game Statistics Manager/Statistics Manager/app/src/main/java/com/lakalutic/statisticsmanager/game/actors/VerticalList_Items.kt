package com.lakalutic.statisticsmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.lakalutic.statisticsmanager.game.manager.SpriteManager
 import com.lakalutic.statisticsmanager.game.utils.actor.disable
 import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
 import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedGroup

class VerticalList_Items: AdvancedGroup() {

    private val vg = VerticalGroup(30f, startGap = 120f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.ListRegion.ITEMS.regionList.shuffled().onEach { itemdosik ->
                vg.addActor(Image(itemdosik).apply {
                    setSize(720f, 160f)
                    setOnClickListener {
                        this@VerticalList_Items.disable()
                        block()
                    }
                })
            }

        }
    }

    fun update() {
        vg.clearChildren()

        SpriteManager.ListRegion.ITEMS.regionList.shuffled().onEach { itemdosik ->
            vg.addActor(Image(itemdosik).apply {
                setSize(720f, 160f)
                setOnClickListener {
                    this@VerticalList_Items.disable()
                    block()
                }
            })
        }
    }

}