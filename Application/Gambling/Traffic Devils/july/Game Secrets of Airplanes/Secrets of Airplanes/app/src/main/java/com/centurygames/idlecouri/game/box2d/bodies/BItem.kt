package com.centurygames.idlecouri.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.centurygames.idlecouri.game.actors.AImage
import com.centurygames.idlecouri.game.box2d.AbstractBody
import com.centurygames.idlecouri.game.utils.advanced.AdvancedBox2dScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 4f
        friction    = 0.4f
        restitution = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.alpha.items.random())

    var isOnStart = AtomicBoolean(true)

}