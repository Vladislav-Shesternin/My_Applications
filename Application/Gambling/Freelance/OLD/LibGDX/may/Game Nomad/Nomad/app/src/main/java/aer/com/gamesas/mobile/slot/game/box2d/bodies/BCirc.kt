package aer.com.gamesas.mobile.slot.game.box2d.bodies

import aer.com.gamesas.mobile.slot.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import aer.com.gamesas.mobile.slot.game.box2d.AbstractBody
import aer.com.gamesas.mobile.slot.game.box2d.BodyId
import aer.com.gamesas.mobile.slot.game.box2d.BodyId.*
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedBox2dScreen

class BCirc(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = CIRC
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.4f
        friction    = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.CIRCLE.region)


}