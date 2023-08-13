package com.lakalutic.statisticsmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.lakalutic.statisticsmanager.game.actors.checkbox.ACheckBox
 import com.lakalutic.statisticsmanager.game.actors.checkbox.ACheckBoxStyle
 import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedGroup

class VerticalList_CBox: AdvancedGroup() {

    private val vg = VerticalGroup(26f, startGap = 150f)
    private val sp = ScrollPane(vg)

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            listOf(
                ACheckBoxStyle.cb1,
                ACheckBoxStyle.cb2,
                ACheckBoxStyle.cb3,
                ACheckBoxStyle.cb4,
                ACheckBoxStyle.cb5,
                ACheckBoxStyle.cb6,
                ACheckBoxStyle.cb7,
            ).shuffled().onEach { stl ->
                vg.addActor(ACheckBox(stl).apply {
                    setSize(720f, 97f)
                })
            }

        }
    }

//    fun update() {
//        vg.clearChildren()
//
//        SpriteManager.ListRegion.COLORS.regionList.shuffled().onEach { itemdosik ->
//            vg.addActor(Image(itemdosik).apply {
//                setSize(720f, 175f)
//                setOnClickListener {
//                    this@VerticalList_CBox.disable()
//                    block()
//                }
//            })
//        }
//    }

}