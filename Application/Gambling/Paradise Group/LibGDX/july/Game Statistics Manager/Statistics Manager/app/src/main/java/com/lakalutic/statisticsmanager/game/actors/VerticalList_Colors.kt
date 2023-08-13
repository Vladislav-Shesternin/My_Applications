package com.lakalutic.statisticsmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.lakalutic.statisticsmanager.game.manager.SpriteManager
 import com.lakalutic.statisticsmanager.game.utils.actor.disable
 import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
 import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedGroup

class VerticalList_Colors: AdvancedGroup() {

    private val vg = VerticalGroup(26f, startGap = 100f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.ListRegion.COLORS.regionList.shuffled().onEach { itemdosik ->
                vg.addActor(Image(itemdosik).apply {
                    setSize(720f, 175f)
                    setOnClickListener {
                        this@VerticalList_Colors.disable()
                        block()
                    }
                })
            }

        }
    }

    fun update() {
        vg.clearChildren()

        SpriteManager.ListRegion.COLORS.regionList.shuffled().onEach { itemdosik ->
            vg.addActor(Image(itemdosik).apply {
                setSize(720f, 175f)
                setOnClickListener {
                    this@VerticalList_Colors.disable()
                    block()
                }
            })
        }
    }

}