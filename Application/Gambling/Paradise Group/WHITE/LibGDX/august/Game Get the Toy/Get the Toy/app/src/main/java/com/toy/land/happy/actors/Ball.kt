package com.toy.land.happy.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.toy.land.happy.advanced.AdvancedGroup
import com.toy.land.happy.assets.SpriteManager
import com.toy.land.happy.utils.CHILD_BALL_H
import com.toy.land.happy.utils.CHILD_BALL_W
import com.toy.land.happy.utils.CHILD_BALL_X
import com.toy.land.happy.utils.CHILD_BALL_Y
import com.toy.land.happy.utils.TOY_H
import com.toy.land.happy.utils.TOY_W
import com.toy.land.happy.utils.TOY_X
import com.toy.land.happy.utils.TOY_Y
import com.toy.land.happy.utils.setBoundsFigmaY

class Ball : AdvancedGroup() {

    private val glow = Image(SpriteManager.glow).apply { isVisible = false }
    private val toy = Image().apply { isVisible = false }

    var isEnable = false
        private set

    var isWin = false
        private set



    override fun sizeChanged() {
        if (width > 0 && height > 0){
            addAndFillActor(glow)
            addActors(getActors())
        }
    }



    private fun getActors() = listOf<Actor>(
        setUpToy(),
        setUpBall(),
    )



    private fun setUpToy() = toy.apply {
        setBoundsFigmaY(TOY_X, TOY_Y, TOY_W, TOY_H, this@Ball.height)
    }

    private fun setUpBall() = Image(SpriteManager.ball).apply {
        setBoundsFigmaY(CHILD_BALL_X, CHILD_BALL_Y, CHILD_BALL_W, CHILD_BALL_H, this@Ball.height)
    }



    fun enable() {
        isWin = false
        isEnable = true
        glow.isVisible = true
        toy.isVisible = true
    }

    fun disable() {
        isEnable = false
        glow.isVisible = false
        toy.isVisible = false
        toy.drawable = TextureRegionDrawable(SpriteManager.none)
    }

    fun setToyRegion(region: TextureRegion?) {
        region?.let { isWin = true }
        toy.drawable = TextureRegionDrawable(region ?: SpriteManager.none)
    }

}