package com.mesga.moolahit.game.box2d.bodies.balance

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.mesga.moolahit.game.box2d.bodies.AbstractBody
import com.mesga.moolahit.game.box2d.bodies.BodyId.*

class Balance: AbstractBody() {
    override var id            = BALANCE
    override val name          = "Balance"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        restitution = 0.2f
        friction = 1f
    }
    override val collisionList = mutableListOf(BONUS)

}