package com.tropical.treasure.catcher.actors

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.tropical.treasure.catcher.assets.SpriteManager
import com.tropical.treasure.catcher.utils.BIRD_H
import com.tropical.treasure.catcher.utils.BIRD_MAX_X
import com.tropical.treasure.catcher.utils.BIRD_MIN_X
import com.tropical.treasure.catcher.utils.BIRD_Y
import com.tropical.treasure.catcher.utils.getFigmaY

class Bird : Image() {

    private val birdY = getFigmaY(BIRD_Y, BIRD_H)

    private val flyAnimation = Animation(0.09f, *SpriteManager.birdList.toTypedArray())

    private var flyTime = 0f



    fun renderFly(time: Float) {
        drawable = TextureRegionDrawable(flyAnimation.getKeyFrame(flyTime, true))
        flyTime += time
    }



    fun fly(){
        addAction(Actions.forever(
            Actions.sequence(
                Actions.moveTo(BIRD_MAX_X, birdY, 5f),
                Actions.moveTo(BIRD_MIN_X, birdY, 5f),
            )
        ))
    }

}