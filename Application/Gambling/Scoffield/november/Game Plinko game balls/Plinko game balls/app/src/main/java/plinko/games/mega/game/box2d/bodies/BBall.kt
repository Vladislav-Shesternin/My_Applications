package plinko.games.mega.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.games.mega.game.actors.image.AImage
import plinko.games.mega.game.box2d.AbstractBody
import plinko.games.mega.game.box2d.BodyId
import plinko.games.mega.game.screens.levelBallIndex
import plinko.games.mega.game.utils.advanced.AdvancedBox2dScreen
import plinko.games.mega.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        restitution = 0.4f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d,
        if (levelBallIndex >= 0) screenBox2d.game.splashAssets.I_LIST[levelBallIndex]
        else screenBox2d.game.splashAssets.I_LIST.random()
    )

    val atomBool = AtomicBoolean(true)

}