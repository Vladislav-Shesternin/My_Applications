package plinko.originalwin.com.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import plinko.originalwin.com.game.actors.AGreenCoin
import plinko.originalwin.com.game.actors.image.AImage
import plinko.originalwin.com.game.box2d.AbstractBody
import plinko.originalwin.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup

class BGreenCoin(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor = true
    }

    override var actor: AdvancedGroup? = AGreenCoin(screenBox2d)

    fun getActor(): AGreenCoin? = actor as? AGreenCoin

    var value: Int = 0
        private set

    fun setValue(value: Int) {
        this.value = value
        getActor()?.setValue(value)
    }

}