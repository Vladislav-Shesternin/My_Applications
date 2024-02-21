package com.jungle.jumping.bird.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jungle.jumping.bird.game.box2d.AbstractBody
import com.jungle.jumping.bird.game.box2d.BodyId
import com.jungle.jumping.bird.game.box2d.WorldUtil

class Border(override val worldUtil: WorldUtil): AbstractBody() {

    override var id         = BodyId.Border
    override val name       = "Border"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BodyId.Bird)

}