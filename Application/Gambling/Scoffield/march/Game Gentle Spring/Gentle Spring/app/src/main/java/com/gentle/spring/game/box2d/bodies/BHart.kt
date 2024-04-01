package com.gentle.spring.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.gentle.spring.game.actors.AImage
import com.gentle.spring.game.box2d.AbstractBody
import com.gentle.spring.game.utils.advanced.AdvancedBox2dScreen
import com.gentle.spring.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BHart(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.harts.random())

    // Field
    val atomicBoolean = AtomicBoolean(true)

}