package com.fellinger.yeasman.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.fellinger.yeasman.game.manager.SpriteManager
import com.fellinger.yeasman.game.utils.actor.setBounds
import com.fellinger.yeasman.game.utils.advanced.AdvancedGroup
import com.fellinger.yeasman.game.utils.Layout.JetAnim as LJ

class JetAnim: AdvancedGroup() {

    private val fireAnim = Animation(0.040f, *SpriteManager.ListRegion.FIRE.regionList.toTypedArray())
    private val manAnim  = Animation(0.050f, *SpriteManager.ListRegion.MAN.regionList.toTypedArray())

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
        manImage.drawable = TextureRegionDrawable(manAnim.getKeyFrame(time, true))
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