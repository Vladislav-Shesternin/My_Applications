package com.jettylucketjet1wincasino.onewinslots1win.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Jet as LJ

class Jet: AdvancedGroup() {

    private val fireAnim = Animation(0.047f, *SpriteManager.ListRegion.FIRE.regionList.toTypedArray())
    private val manAnim  = Animation(0.037f, *SpriteManager.ListRegion.MAN.regionList.toTypedArray()).apply { playMode = Animation.PlayMode.LOOP_PINGPONG }

    private val fireImage = Image()
    private val manImage  = Image()

    private var time = 0f


    override fun sizeChanged() {
        super.sizeChanged()
        if(width > 0 && height > 0) addActorsOnGroup()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        fireImage.drawable = TextureRegionDrawable(fireAnim.getKeyFrame(time, true))
        manImage.drawable  = TextureRegionDrawable(manAnim.getKeyFrame(time))
        time += Gdx.graphics.deltaTime
    }

    private fun addActorsOnGroup() {
        addFire()
        addMan()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addFire() {
        addActor(fireImage)
        fireImage.setBounds(LJ.fire)
    }

    private fun addMan() {
        addActor(manImage)
        manImage.setBounds(LJ.man)
    }

}