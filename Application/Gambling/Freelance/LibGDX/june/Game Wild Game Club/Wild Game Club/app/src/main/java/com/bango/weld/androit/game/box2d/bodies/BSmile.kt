package com.bango.weld.androit.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bango.weld.androit.game.actors.image.AImage
import com.bango.weld.androit.game.box2d.AbstractBody
import com.bango.weld.androit.game.box2d.BodyId
import com.bango.weld.androit.game.box2d.BodyId.*
import com.bango.weld.androit.game.utils.actor.disable
import com.bango.weld.androit.game.utils.advanced.AdvancedBox2dScreen
import java.util.concurrent.atomic.AtomicBoolean

class BSmile(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = SMILE
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(RUKA)
    override val actor = AImage().apply { disable() }

    var smileIndex = -1
    var isEmit: AtomicBoolean = AtomicBoolean(true)

}