package com.danila.cryptotracker.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.danila.cryptotracker.game.actors.scroll.HorizontalGroup
 import com.danila.cryptotracker.game.manager.SpriteManager
 import com.danila.cryptotracker.game.utils.actor.setOnClickListener
 import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup

class HorizontalKadgatCryptoList: AdvancedGroup() {

    private val hg = HorizontalGroup(36f)
    private val sp = ScrollPane(hg)

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.ListRegion.KADGAT.regionList.shuffled().onEach {
                hg.addActor(Image(it).apply {
                    setSize(236f, 248f)
                    setOnClickListener {
                        //this@SuperScrollPane.disable()
                        //NavigationManager.navigate()
                    }
                })
            }

        }
    }

}