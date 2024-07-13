package com.kongregate.mobile.burrit.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.kongregate.mobile.burrit.game.actors.AImage
import com.kongregate.mobile.burrit.game.box2d.AbstractBody
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedBox2dScreen
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BPrc(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "prc"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.4f
        restitution = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.alpha.prc)

}