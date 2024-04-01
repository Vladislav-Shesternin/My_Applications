package com.farello.rocketing.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.farello.rocketing.game.actors.AImage
import com.farello.rocketing.game.box2d.AbstractBody
import com.farello.rocketing.game.utils.advanced.AdvancedBox2dScreen
import com.farello.rocketing.game.utils.advanced.AdvancedGroup

class BStatic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()

}