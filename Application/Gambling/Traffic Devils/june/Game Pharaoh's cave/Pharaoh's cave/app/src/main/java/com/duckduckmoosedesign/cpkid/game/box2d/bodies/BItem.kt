package com.duckduckmoosedesign.cpkid.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.duckduckmoosedesign.cpkid.game.actors.AImage
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedBox2dScreen
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 5f
        friction = 0.4f
        restitution = (1..10).random() / 10f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAss.items.random())

    var isOnStart = AtomicBoolean(true)

}