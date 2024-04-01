package com.superstar.superspring.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.superstar.superspring.game.actors.AImage
import com.superstar.superspring.game.box2d.AbstractBody
import com.superstar.superspring.game.box2d.BodyId
import com.superstar.superspring.game.utils.advanced.AdvancedBox2dScreen
import com.superstar.superspring.game.utils.advanced.AdvancedGroup

class BBatut(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.5f
        friction    = 0.4f
    }

    override var id            = BodyId.BATUT
    override val collisionList = mutableListOf(BodyId.SPRING)


}