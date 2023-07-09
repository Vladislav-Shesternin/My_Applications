package com.bango.weld.androit.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bango.weld.androit.game.actors.image.AImage
import com.bango.weld.androit.game.box2d.AbstractBody
import com.bango.weld.androit.game.box2d.BodyId
import com.bango.weld.androit.game.box2d.BodyId.*
import com.bango.weld.androit.game.manager.SpriteManager
import com.bango.weld.androit.game.utils.actor.disable
import com.bango.weld.androit.game.utils.advanced.AdvancedBox2dScreen
import java.util.concurrent.atomic.AtomicBoolean

class BRight(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = RUKA
    override val name       = "right"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        restitution = 1f
        friction    = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(SMILE)
    override val actor = AImage(SpriteManager.GameRegion.RIGHT.region).apply { disable() }

}