package plinko.testyouluck.com.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.testyouluck.com.game.actors.image.AImage
import plinko.testyouluck.com.game.box2d.AbstractBody
import plinko.testyouluck.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

var StaticBallType: BallType = BallType.DEFAULT

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().setUpByType()

    private val assets = screenBox2d.game.gameAssets

    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())

    // Field
    val atomicBoolean = AtomicBoolean(true)


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun FixtureDef.setUpByType(): FixtureDef {
        when (StaticBallType) {
            BallType.DEFAULT -> {
                density = 1f
                restitution = 0.2f
                friction = 0.2f
            }

            BallType._5000   -> {
                density = 1.5f
                restitution = 0.3f
                friction = 0.3f
            }

            BallType._8500   -> {
                density = 2.5f
                restitution = 0.4f
                friction = 0.4f
            }

            BallType._10000  -> {
                density = 3f
                restitution = 0.5f
                friction = 0.5f
            }
        }

        return this
    }

    private fun getRegionByType(): TextureRegion = when (StaticBallType) {
        BallType.DEFAULT -> assets.BALL_DEFAULT
        BallType._5000   -> assets.BALL_5000
        BallType._8500   -> assets.BALL_8500
        BallType._10000  -> assets.BALL_10000
    }

}

enum class BallType {
    DEFAULT, _5000, _8500, _10000
}