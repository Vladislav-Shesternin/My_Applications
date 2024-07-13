package com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.AbstractBody
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedBox2dScreen

class BStatic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()

}