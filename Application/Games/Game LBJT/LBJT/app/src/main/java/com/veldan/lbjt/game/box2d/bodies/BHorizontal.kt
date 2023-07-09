package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen

class BHorizontal(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "horizontal"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
}