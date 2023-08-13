package com.prochenkoa.businessplanner.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.prochenkoa.businessplanner.game.manager.SpriteManager
 import com.prochenkoa.businessplanner.game.screens.imgIndex
 import com.prochenkoa.businessplanner.game.utils.actor.disable
 import com.prochenkoa.businessplanner.game.utils.actor.setOnClickListener
 import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedGroup
 import com.prochenkoa.businessplanner.game.utils.region

class VerList: AdvancedGroup() {

    private val listIII by lazy { listOf(
        SpriteManager.EnumTexture.i1.data.texture.region,
        SpriteManager.EnumTexture.i2.data.texture.region,
        SpriteManager.EnumTexture.i3.data.texture.region,
        SpriteManager.EnumTexture.i4.data.texture.region,
        SpriteManager.EnumTexture.i5.data.texture.region,
        SpriteManager.EnumTexture.i6.data.texture.region,
        SpriteManager.EnumTexture.i7.data.texture.region,
        SpriteManager.EnumTexture.i8.data.texture.region,
    ) }

    private val vg = VerticalGroup(30f, startGap = 120f)
    private val sp = ScrollPane(vg)

    var block: () -> Unit = {

    }

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            (0..7).shuffled().onEach { iiiiiii ->
                vg.addActor(Image(listIII[iiiiiii]).apply {
                    setSize(657f, 657f)
                    setOnClickListener {
                        this@VerList.disable()
                        block()
                        imgIndex = iiiiiii
                    }
                })
            }

        }
    }

    fun idiPogulaySinok() {
        vg.clearChildren()

        (0..7).shuffled().onEach { iiiiiii ->
            vg.addActor(Image(listIII[iiiiiii]).apply {
                setSize(657f, 657f)
                setOnClickListener {
                    this@VerList.disable()
                    block()
                    imgIndex = iiiiiii
                }
            })
        }
    }

}