package com.jumping.cubuletus.game.box2d.bodies.frame

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jumping.cubuletus.game.box2d.bodies.AbstractBody
import com.jumping.cubuletus.game.box2d.bodies.BodyId.*

class BFrame: AbstractBody() {
    override var id            = FRAME
    override val name          = "Frame"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        friction    = 0.5f
        restitution = 0.5f
    }
    override val collisionList = mutableListOf(COUB)

}