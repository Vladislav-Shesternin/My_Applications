package com.destroyer.buildings.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.destroyer.buildings.game.actors.AImage
import com.destroyer.buildings.game.box2d.AbstractBody
import com.destroyer.buildings.game.box2d.BodyId
import com.destroyer.buildings.game.utils.advanced.AdvancedBox2dScreen
import com.destroyer.buildings.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BBuild(override val screenBox2d: AdvancedBox2dScreen, region: TextureRegion): AbstractBody() {
    override val name       = "r"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.3f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, region)

    override var id = BodyId.BUILD
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.BUILD, BodyId.STONE)

}