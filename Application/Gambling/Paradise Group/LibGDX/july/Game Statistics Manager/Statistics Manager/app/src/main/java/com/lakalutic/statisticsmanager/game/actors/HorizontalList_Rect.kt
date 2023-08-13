package com.lakalutic.statisticsmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.lakalutic.statisticsmanager.game.manager.SpriteManager
 import com.lakalutic.statisticsmanager.game.utils.actor.disable
 import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
 import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedGroup

class HorizontalList_Rect: AdvancedGroup() {

    private val vg = HorizontalGroup(26f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.ListRegion.RECT.regionList.shuffled().onEach { itemdosik ->
                vg.addActor(Image(itemdosik).apply {
                    setSize(530f, 295f)
                    setOnClickListener {
                        this@HorizontalList_Rect.disable()
                        block()
                    }
                })
            }

        }
    }

    fun update() {
        vg.clearChildren()

        SpriteManager.ListRegion.RECT.regionList.shuffled().onEach { itemdosik ->
            vg.addActor(Image(itemdosik).apply {
                setSize(530f, 295f)
                setOnClickListener {
                    this@HorizontalList_Rect.disable()
                    block()
                }
            })
        }
    }

}