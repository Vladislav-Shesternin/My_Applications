package com.minimal.tennis.fortwo.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.minimal.tennis.fortwo.game.actors.AShar
import com.minimal.tennis.fortwo.game.actors.image.AImage
import com.minimal.tennis.fortwo.game.box2d.AbstractBody
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedBox2dScreen
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.7f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.ball)

}