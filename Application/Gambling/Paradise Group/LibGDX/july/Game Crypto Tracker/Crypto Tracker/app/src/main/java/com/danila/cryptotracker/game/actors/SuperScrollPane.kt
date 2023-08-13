//package com.danila.cryptotracker.game.actors
//
// import com.badlogic.gdx.scenes.scene2d.ui.Image
// import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
// import com.danila.cryptotracker.game.actors.scroll.VerticalGroup
// import com.danila.cryptotracker.game.manager.SpriteManager
// import com.danila.cryptotracker.game.utils.actor.setOnClickListener
// import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
//
//class SuperScrollPane: AdvancedGroup() {
//
//    private val vg = VerticalGroup(30f)
//    private val sp = ScrollPane(vg)
//
//    override fun sizeChanged() {
//        if (width > 0 && height > 0) {
//            addAndFillActor(sp)
//
//            SpriteManager.ListRegion.IRISKI.regionList.shuffled().onEach {
//                vg.addActor(Image(it).apply {
//                    setSize(641f, 77f)
//                    setOnClickListener {
//                        //this@SuperScrollPane.disable()
//                        //NavigationManager.navigate()
//                    }
//                })
//            }
//
//        }
//    }
//
//}