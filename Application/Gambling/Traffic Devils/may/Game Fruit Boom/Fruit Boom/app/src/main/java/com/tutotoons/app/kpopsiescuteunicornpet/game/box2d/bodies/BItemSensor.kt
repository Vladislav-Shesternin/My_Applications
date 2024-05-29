package com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.AbstractBody
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.BodyId
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedBox2dScreen

class BItemSensor(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "h"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor = true
    }

    override var id: String = BodyId.ITEM_SENSOR
    override val collisionList: MutableList<String> = mutableListOf(BodyId.ITEM_START)

}