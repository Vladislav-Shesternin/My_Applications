package com.radarada.avia.game.box2d.bodies

import com.radarada.avia.game.actors.image.AImage
import com.radarada.avia.game.box2d.AbstractBody
import com.radarada.avia.game.box2d.BodyId
import com.radarada.avia.game.utils.advanced.AdvancedBox2dScreen
import com.radarada.avia.game.utils.advanced.AdvancedGroup
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

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.moneyList.random())

    override var id            = BodyId.Game.COIN
    override val collisionList = mutableListOf(BodyId.Game.AVIA)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}