package com.ottplay.ottpl.game.box2d.bodies

import com.ottplay.ottpl.game.actors.image.AImage
import com.ottplay.ottpl.game.box2d.AbstractBody
import com.ottplay.ottpl.game.box2d.BodyId
import com.ottplay.ottpl.game.utils.advanced.AdvancedBox2dScreen
import com.ottplay.ottpl.game.utils.advanced.AdvancedGroup
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import java.util.concurrent.atomic.AtomicBoolean

class BCoin(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.moneyList.random())

    override var id            = BodyId.Game.COIN
    override val collisionList = mutableListOf(BodyId.Game.AVIA)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}