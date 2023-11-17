package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.lbjt.game.actors.button.AButton_Monetization
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup

class BMonetizationBtn(
    override val screenBox2d: AdvancedBox2dScreen,
    monetizationType: AButton_Monetization.Static.Type,
): AbstractBody() {
    override val name       = "monetization_btn"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.4f
        friction    = 0.4f
    }
    override var actor: AdvancedGroup? = AButton_Monetization(screenBox2d, monetizationType)

    fun getActor(): AButton_Monetization? = actor as? AButton_Monetization
}