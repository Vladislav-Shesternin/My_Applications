package com.guardians.galaxyguano.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.guardians.galaxyguano.game.actors.AImage
import com.guardians.galaxyguano.game.box2d.AbstractBody
import com.guardians.galaxyguano.game.box2d.BodyId
import com.guardians.galaxyguano.game.utils.advanced.AdvancedBox2dScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedGroup

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        gravityScale = 0.2f
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.4f
        friction    = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.ball)

    override var id = BodyId.BALL
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.ASTEROID, BodyId.PLATFORM, BodyId.DOWN)

}