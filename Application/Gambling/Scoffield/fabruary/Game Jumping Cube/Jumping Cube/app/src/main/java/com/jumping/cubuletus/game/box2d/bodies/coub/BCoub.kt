package com.jumping.cubuletus.game.box2d.bodies.coub

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jumping.cubuletus.game.actors.ACoub
import com.jumping.cubuletus.game.box2d.bodies.AbstractBody
import com.jumping.cubuletus.game.box2d.bodies.BodyId.*
import com.jumping.cubuletus.game.util.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BCoub: AbstractBody() {
    override var id            = COUB
    override val name          = "Coub"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density     = 1f
        friction    = 0.7f
        restitution = 0.5f
    }
    override val collisionList = mutableListOf(BATUT, COIN, FRAME)

    override val actor = ACoub()

    var isOnGround = false

    override fun beginContact(contactBody: AbstractBody) {
        when(contactBody.id) {
            FRAME, BATUT -> { isOnGround = true }
            else         -> {}
        }
        super.beginContact(contactBody)
    }

    override fun endContact(contactBody: AbstractBody) {
        when(contactBody.id) {
            FRAME, BATUT -> {
                coroutine.launch {
                    delay(100)
                    runGDX { isOnGround = false }
                }
            }
            else         -> {}
        }
        super.endContact(contactBody)
    }

    override fun render(deltaTime: Float) {
        if (body.linearVelocity.x == 0f && body.linearVelocity.y == 0f) isOnGround = true
        super.render(deltaTime)
    }

}