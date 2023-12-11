package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.lbjt.game.actors.APracticalSettings
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup

class BPracticalSettings(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "practical_btn"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 5f
        restitution = 0.3f
        friction    = 0.3f
    }
    override var actor: AdvancedGroup? = APracticalSettings(screenBox2d)

    fun getActor(): APracticalSettings? = actor as? APracticalSettings

}