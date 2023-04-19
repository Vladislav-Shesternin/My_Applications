package com.veldan.spinia.actors

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.veldan.spinia.assets.SpriteManager
import com.veldan.spinia.utils.*
import kotlinx.coroutines.*
import kotlin.random.Random

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