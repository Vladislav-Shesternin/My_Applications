package plinko.gameballs.nine.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.gameballs.nine.game.actors.image.AImage
import plinko.gameballs.nine.game.box2d.AbstractBody
import plinko.gameballs.nine.game.utils.advanced.AdvancedBox2dScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedGroup

class BRect(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "rect"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()

//    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.WHITE)
}