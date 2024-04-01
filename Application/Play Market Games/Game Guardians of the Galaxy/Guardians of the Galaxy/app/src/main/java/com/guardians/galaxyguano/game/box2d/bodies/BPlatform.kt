package com.guardians.galaxyguano.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.guardians.galaxyguano.game.actors.AImage
import com.guardians.galaxyguano.game.box2d.AbstractBody
import com.guardians.galaxyguano.game.box2d.BodyId
import com.guardians.galaxyguano.game.utils.advanced.AdvancedBox2dScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedGroup

class BPlatform(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "platform"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.95f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.platform)

    override var id = BodyId.PLATFORM
    override val collisionList = mutableListOf(BodyId.BALL)

}