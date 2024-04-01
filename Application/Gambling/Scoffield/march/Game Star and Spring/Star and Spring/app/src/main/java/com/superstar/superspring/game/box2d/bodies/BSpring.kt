package com.superstar.superspring.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.superstar.superspring.game.actors.AImage
import com.superstar.superspring.game.box2d.AbstractBody
import com.superstar.superspring.game.box2d.BodyId
import com.superstar.superspring.game.utils.advanced.AdvancedBox2dScreen
import com.superstar.superspring.game.utils.advanced.AdvancedGroup

class BSpring(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "sp"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.spring)

    override var id            = BodyId.SPRING
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.BATUT, BodyId.STAR)

}