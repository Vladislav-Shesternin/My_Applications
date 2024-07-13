package com.earlymorningstudio.championsofa.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.earlymorningstudio.championsofa.game.actors.AImage
import com.earlymorningstudio.championsofa.game.box2d.AbstractBody
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedBox2dScreen
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedGroup

class BParasha(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 0.2f
        friction    = 0.5f
        restitution = 0.6f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.LICHIKO.uubm)

}