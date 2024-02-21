package plinko.originalwin.com.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.originalwin.com.game.actors.image.AImage
import plinko.originalwin.com.game.box2d.AbstractBody
import plinko.originalwin.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 15f
        restitution = 0.2f
        friction    = 0f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.coin)

}