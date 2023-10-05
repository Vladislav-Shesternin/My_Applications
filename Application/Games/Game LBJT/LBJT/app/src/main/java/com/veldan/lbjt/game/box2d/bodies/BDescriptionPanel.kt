package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.lbjt.game.actors.ADescriptionPanel
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup

class BDescriptionPanel(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "description_panel"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.4f
    }
    override var actor: AdvancedGroup? = ADescriptionPanel(screenBox2d)

    fun getActor(): ADescriptionPanel? = actor as? ADescriptionPanel
}