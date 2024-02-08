package plinko.testyouluck.com.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.testyouluck.com.game.actors.image.AImage
import plinko.testyouluck.com.game.box2d.AbstractBody
import plinko.testyouluck.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedGroup

class BBalk(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = (10..40).random() / 100f
    }

}