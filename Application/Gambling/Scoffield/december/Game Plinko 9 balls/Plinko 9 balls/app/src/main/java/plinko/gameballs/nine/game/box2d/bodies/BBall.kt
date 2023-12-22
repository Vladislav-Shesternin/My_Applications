package plinko.gameballs.nine.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.gameballs.nine.game.actors.image.AImage
import plinko.gameballs.nine.game.box2d.AbstractBody
import plinko.gameballs.nine.game.screens.selectedBall
import plinko.gameballs.nine.game.utils.advanced.AdvancedBox2dScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 3f
        restitution = 0.5f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, selectedBall ?: screenBox2d.game.gameAssets.BALLS.random())

    val atomicBoolean = AtomicBoolean(true)

}