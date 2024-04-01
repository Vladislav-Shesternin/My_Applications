package com.bottleber.lebeler.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bottleber.lebeler.game.box2d.AbstractBody
import com.bottleber.lebeler.game.utils.advanced.AdvancedBox2dScreen

class BBoxVertical(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "v_box"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.3f
        friction    = 0.2f

    }

}