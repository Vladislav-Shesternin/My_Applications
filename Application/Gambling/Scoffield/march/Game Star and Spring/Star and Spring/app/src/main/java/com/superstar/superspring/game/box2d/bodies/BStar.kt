package com.superstar.superspring.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.superstar.superspring.game.actors.AImage
import com.superstar.superspring.game.box2d.AbstractBody
import com.superstar.superspring.game.box2d.BodyId
import com.superstar.superspring.game.utils.advanced.AdvancedBox2dScreen
import com.superstar.superspring.game.utils.advanced.AdvancedGroup

class BStar(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "st"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.2f
        friction    = 0.7f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.star)

    override var id            = BodyId.STAR
    override val collisionList = mutableListOf(BodyId.SPRING)


}