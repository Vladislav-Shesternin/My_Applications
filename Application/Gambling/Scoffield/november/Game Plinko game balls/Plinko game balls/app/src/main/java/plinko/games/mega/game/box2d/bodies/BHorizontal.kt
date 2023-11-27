package plinko.games.mega.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.games.mega.game.box2d.AbstractBody
import plinko.games.mega.game.box2d.BodyId
import plinko.games.mega.game.utils.advanced.AdvancedBox2dScreen

class BHorizontal(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "hor"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor = true
    }

}