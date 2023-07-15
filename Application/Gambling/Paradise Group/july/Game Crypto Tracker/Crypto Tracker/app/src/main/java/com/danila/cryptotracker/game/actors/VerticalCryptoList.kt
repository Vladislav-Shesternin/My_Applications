package com.danila.cryptotracker.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.danila.cryptotracker.game.actors.scroll.HorizontalGroup
 import com.danila.cryptotracker.game.actors.scroll.VerticalGroup
 import com.danila.cryptotracker.game.manager.SpriteManager
 import com.danila.cryptotracker.game.utils.actor.disable
 import com.danila.cryptotracker.game.utils.actor.setOnClickListener
 import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup

class VerticalCryptoList: AdvancedGroup() {

    private val vg = VerticalGroup(32f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            SpriteManager.ListRegion.PAMOGUGON.regionList.shuffled().onEach {
                vg.addActor(Image(it).apply {
                    setSize(612f, 101f)
                    setOnClickListener {
                        this@VerticalCryptoList.disable()
                        block()
                    }
                })
            }

        }
    }

}