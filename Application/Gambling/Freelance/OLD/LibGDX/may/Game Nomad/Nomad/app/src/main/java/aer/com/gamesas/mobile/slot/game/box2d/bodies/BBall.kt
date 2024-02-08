package aer.com.gamesas.mobile.slot.game.box2d.bodies

import aer.com.gamesas.mobile.slot.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import aer.com.gamesas.mobile.slot.game.box2d.AbstractBody
import aer.com.gamesas.mobile.slot.game.box2d.BodyId
import aer.com.gamesas.mobile.slot.game.box2d.BodyId.*
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedBox2dScreen
import com.badlogic.gdx.graphics.g2d.TextureRegion

class BBall(override val screenBox2d: AdvancedBox2dScreen, val type: Type): AbstractBody() {

    override var id         = BALL
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = type.density
        restitution = type.restitution
        friction    = type.friction
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(RECT, CIRC)
    override val actor = AImage(type.region)


    enum class Type(
        val region: TextureRegion,
        val density: Float,
        val restitution: Float,
        val friction: Float,
    ) {
        GOLD(SpriteManager.GameRegion.SHAR_GOLD.region, 5f, 0.4f, 0.8f),
        GREEN(SpriteManager.GameRegion.SHAR_GREEN.region, 2.5f, 0.8f, 0.4f),
    }
}