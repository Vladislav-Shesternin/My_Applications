package com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.bodies

import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.ACircle
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AImage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.AbstractBody
import com.amayasoft.cars.kids.toddlers.garage.ga.game.screens.isBlue
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedBox2dScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BCircle(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1.2f
        friction    = 0.4f
        restitution = 0.7f
    }

    override var actor: AdvancedGroup? = ACircle(screenBox2d,
        if(isBlue) screenBox2d.game.G1.qwe else screenBox2d.game.G1.fghf
    )

}