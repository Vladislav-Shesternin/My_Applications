package com.levitation.plates.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.levitation.plates.game.actors.AImage
import com.levitation.plates.game.box2d.AbstractBody
import com.levitation.plates.game.box2d.BodyId
import com.levitation.plates.game.utils.advanced.AdvancedBox2dScreen
import com.levitation.plates.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BPlanet(override val screenBox2d: AdvancedBox2dScreen, private val index: Int): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.8f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.planets[index])

    override val collisionList = mutableListOf(BodyId.Cosmos.TARELKA, BodyId.Cosmos.PLANETA, BodyId.Cosmos.VOROTA)

    var isStart = AtomicBoolean(true)

}