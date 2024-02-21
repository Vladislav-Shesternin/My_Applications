package plinko.originalwin.com.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.originalwin.com.game.box2d.AbstractBody
import plinko.originalwin.com.game.utils.advanced.AdvancedBox2dScreen

class BPlayground(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "playground"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.2f
    }

}