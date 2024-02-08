package plinko.testyouluck.com.game.box2d.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.testyouluck.com.game.actors.image.AImage
import plinko.testyouluck.com.game.box2d.AbstractBody
import plinko.testyouluck.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedGroup

var StaticIsRed = false

class BPlayground(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "playground"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        if (StaticIsRed) {
            restitution = (10..40).random() / 100f
            friction    = (10..40).random() / 100f
        } else {
            restitution = (10..20).random() / 100f
            friction    = (10..20).random() / 100f
        }
    }

    private val assets = screenBox2d.game.gameAssets

    override var actor: AdvancedGroup? = AImage(screenBox2d, if (StaticIsRed) assets.PLINKO_RED else assets.PLINKO_PURPLE)


    object Static {
        val sizeBalk      get() = if (StaticIsRed) Vector2(34f, 34f) else Vector2(53f, 53f)
        val positionsBalk get() = if (StaticIsRed) listOf<Vector2>(
            Vector2(316f, 1070f),
            Vector2(457f, 1070f),
            Vector2(598f, 1070f),
            Vector2(739f, 1070f),

            Vector2(385f, 930f),
            Vector2(527f, 930f),
            Vector2(669f, 930f),

            Vector2(315f, 790f),
            Vector2(455f, 790f),
            Vector2(595f, 790f),
            Vector2(735f, 790f),

            Vector2(385f, 651f),
            Vector2(527f, 651f),
            Vector2(669f, 651f),

            Vector2(315f, 511f),
            Vector2(456f, 511f),
            Vector2(597f, 511f),
            Vector2(738f, 511f),

            Vector2(384f, 371f),
            Vector2(524f, 371f),
            Vector2(664f, 371f),
        ) else listOf<Vector2>(
            Vector2(300f, 1053f),
            Vector2(439f, 1053f),
            Vector2(587f, 1053f),
            Vector2(736f, 1053f),

            Vector2(370f, 909f),
            Vector2(513f, 909f),
            Vector2(656f, 909f),

            Vector2(308f, 768f),
            Vector2(446f, 768f),
            Vector2(587f, 768f),
            Vector2(728f, 768f),

            Vector2(370f, 622f),
            Vector2(513f, 622f),
            Vector2(656f, 622f),

            Vector2(308f, 487f),
            Vector2(442f, 487f),
            Vector2(576f, 487f),
            Vector2(736f, 487f),

            Vector2(370f, 345f),
            Vector2(523f, 345f),
            Vector2(656f, 345f),
        )
    }

}