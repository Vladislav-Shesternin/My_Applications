package com.my.cooking.chef.kitchen.craze.fe.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractBody
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedBox2dScreen

class BVRect(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "rt"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        friction    = 0.3f
        restitution = 0.6f
    }

}