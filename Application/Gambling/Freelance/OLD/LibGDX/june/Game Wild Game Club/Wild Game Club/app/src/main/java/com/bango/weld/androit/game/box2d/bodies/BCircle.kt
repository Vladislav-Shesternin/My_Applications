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

class BCircle(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = NONE
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf()
    override val actor = AImage().apply { disable() }

}