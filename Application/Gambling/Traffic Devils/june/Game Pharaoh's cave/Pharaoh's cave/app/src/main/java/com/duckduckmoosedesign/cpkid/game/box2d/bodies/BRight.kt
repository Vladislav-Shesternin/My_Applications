package com.duckduckmoosedesign.cpkid.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.duckduckmoosedesign.cpkid.game.actors.AImage
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpkid.game.box2d.BodyId
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedBox2dScreen
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BRight(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "right"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 2.5f
        friction = 0.4f
        restitution = 0.45f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAss.right)

    override var id: String = BodyId.TRIANGLE
    override val collisionList: MutableList<String> = mutableListOf(BodyId.ITEM, BodyId.PARAMON)
}