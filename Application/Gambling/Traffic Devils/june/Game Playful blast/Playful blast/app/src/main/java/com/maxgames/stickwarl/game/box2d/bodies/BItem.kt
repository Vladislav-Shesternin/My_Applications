package com.maxgames.stickwarl.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.maxgames.stickwarl.game.actors.AImage
import com.maxgames.stickwarl.game.box2d.AbstractBody
import com.maxgames.stickwarl.game.utils.advanced.AdvancedBox2dScreen
import com.maxgames.stickwarl.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 5f
        friction    = 0.2f
        restitution = 0.3f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.assets.items.random())

    var isOnStart = AtomicBoolean(true)

}