package atest.btest.lbjttest.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import atest.btest.lbjttest.game.box2d.AbstractBody
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen

class BVertical(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "v"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
}