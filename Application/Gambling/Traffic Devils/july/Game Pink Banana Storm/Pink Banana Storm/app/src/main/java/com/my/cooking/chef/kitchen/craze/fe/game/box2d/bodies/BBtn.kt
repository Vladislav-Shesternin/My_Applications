package com.my.cooking.chef.kitchen.craze.fe.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.my.cooking.chef.kitchen.craze.fe.game.actors.AImage
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractBody
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedBox2dScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedGroup

class BBtn(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "btn"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        friction    = 0.3f
        restitution = 0.6f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allOl.pltfrma)

}