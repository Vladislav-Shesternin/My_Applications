package com.bottleber.lebeler.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bottleber.lebeler.game.actors.AImage
import com.bottleber.lebeler.game.box2d.AbstractBody
import com.bottleber.lebeler.game.box2d.BodyId
import com.bottleber.lebeler.game.utils.advanced.AdvancedBox2dScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedGroup

class BBottle(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "bottle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density  = 1f
        restitution = 0.5f
        friction = 0.1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.bottles.random())

    override var id = BodyId.BOTTLE
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.PRICEL_BOX_DOWN, BodyId.BOTTLE, BodyId.SEPARATOR)

}