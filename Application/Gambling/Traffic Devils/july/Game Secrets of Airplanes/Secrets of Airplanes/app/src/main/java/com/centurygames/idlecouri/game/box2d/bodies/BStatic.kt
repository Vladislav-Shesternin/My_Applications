package com.centurygames.idlecouri.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.centurygames.idlecouri.game.actors.AImage
import com.centurygames.idlecouri.game.box2d.AbstractBody
import com.centurygames.idlecouri.game.utils.advanced.AdvancedBox2dScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BStatic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()

}