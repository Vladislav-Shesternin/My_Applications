package com.levitation.plates.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.levitation.plates.game.actors.AImage
import com.levitation.plates.game.actors.ATarelkaGreen
import com.levitation.plates.game.box2d.AbstractBody
import com.levitation.plates.game.box2d.BodyId
import com.levitation.plates.game.utils.advanced.AdvancedBox2dScreen
import com.levitation.plates.game.utils.advanced.AdvancedGroup

class BTarelkaGreen(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        fixedRotation = true
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        restitution = 0.3f
        friction    = 0.3f
    }

    override var actor: AdvancedGroup? = ATarelkaGreen(screenBox2d)

    override var id            = BodyId.Cosmos.TARELKA
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.Cosmos.TARELKA, BodyId.Cosmos.PLANETA)

}