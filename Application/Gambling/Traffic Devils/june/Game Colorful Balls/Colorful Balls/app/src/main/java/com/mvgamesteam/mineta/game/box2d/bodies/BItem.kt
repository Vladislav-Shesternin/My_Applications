package com.mvgamesteam.mineta.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.mvgamesteam.mineta.game.actors.AImage
import com.mvgamesteam.mineta.game.box2d.AbstractBody
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedBox2dScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1.2f
        friction    = 0.4f
        restitution = 0.7f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.Jer.items.random())

    var isOnStart = AtomicBoolean(true)

}