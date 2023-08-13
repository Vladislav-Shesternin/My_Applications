package com.metanit.metrixmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.metanit.metrixmanager.game.manager.SpriteManager
 import com.metanit.metrixmanager.game.utils.actor.disable
 import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
 import com.metanit.metrixmanager.game.utils.advanced.AdvancedGroup

class DerbiNaVij: AdvancedGroup() {

    private val vg = VerticalGroup(35f, startGap = 100f, endGap = 20f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {  }

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.Bazuki.GAGI.regionList.shuffled().onEach { loto ->
                vg.addActor(Image(loto).apply {
                    setSize(473f, 174f)
                    setOnClickListener {
                        this@DerbiNaVij.disable()
                        block()
                    }
                })
            }

        }
    }
//
//    fun idiPogulaySinok() {
//        vg.clearChildren()
//
//        (0..7).shuffled().onEach { iiiiiii ->
//            vg.addActor(Image(listIII[iiiiiii]).apply {
//                setSize(657f, 657f)
//                setOnClickListener {
//                    this@VerList.disable()
//                    block()
//                    imgIndex = iiiiiii
//                }
//            })
//        }
//    }

}