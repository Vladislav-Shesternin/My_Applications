package com.nicoledeonnit.cryptosignals.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
 import com.nicoledeonnit.cryptosignals.game.utils.actor.disable
 import com.nicoledeonnit.cryptosignals.game.utils.actor.setOnClickListener
 import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedGroup

class PalermonTov: AdvancedGroup() {

    private val vg = VerticalGroup(36f, startGap = 72f)
    private val sp = ScrollPane(vg)

    var bclikaock: () -> Unit = {  }

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            (SpriteManager.FanariK.LOB.regionList + SpriteManager.FanariK.LOB.regionList +
                    SpriteManager.FanariK.LOB.regionList + SpriteManager.FanariK.LOB.regionList).shuffled().onEach { loto ->
                vg.addActor(Image(loto).apply {
                    setSize(741f, 79f)
                    setOnClickListener {
                        this@PalermonTov.disable()
                        bclikaock()
                    }
                })
            }

        }
    }

    fun saksagonkiDen() {
        vg.clearChildren()

        (SpriteManager.FanariK.LOB.regionList + SpriteManager.FanariK.LOB.regionList +
                SpriteManager.FanariK.LOB.regionList + SpriteManager.FanariK.LOB.regionList).shuffled().onEach { loto ->
            vg.addActor(Image(loto).apply {
                setSize(741f, 79f)
                setOnClickListener {
                    this@PalermonTov.disable()
                    bclikaock()
                }
            })
        }
    }

}