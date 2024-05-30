package com.bricks.vs.balls.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bricks.vs.balls.game.actors.AImage
import com.bricks.vs.balls.game.box2d.AbstractBody
import com.bricks.vs.balls.game.box2d.BodyId
import com.bricks.vs.balls.game.utils.advanced.AdvancedBox2dScreen
import com.bricks.vs.balls.game.utils.advanced.AdvancedGroup

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.3f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.assetsAll.ball)

    override var id = BodyId.BALL
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.DYNAMIC)
}