package plinko.games.mega.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.games.mega.game.actors.image.AImage
import plinko.games.mega.game.box2d.AbstractBody
import plinko.games.mega.game.utils.advanced.AdvancedBox2dScreen
import plinko.games.mega.game.utils.advanced.AdvancedGroup

class BWhite(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = (1..6).random()/10f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.SHAR)
}