package com.mesga.moolahit.game.box2d.bodies.bonus

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.mesga.moolahit.game.actors.image.AImage
import com.mesga.moolahit.game.box2d.bodies.AbstractBody
import com.mesga.moolahit.game.box2d.bodies.BodyId.*
import com.mesga.moolahit.game.manager.SpriteManager
import java.util.concurrent.atomic.AtomicBoolean
import com.mesga.moolahit.game.util.Layout.Bonus as LB

class Bonus : AbstractBody() {

    companion object {
        fun getPositionUI() = Vector2().apply {
            x = (LB.bonusStart.x.toInt()..LB.bonusEndX.toInt()).shuffled().first().toFloat()
            y = LB.bonusStart.y
        }
    }

    override var id            = BONUS
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density     = 1f
        restitution = 0.4f
        friction    = 0.3f
    }
    override val collisionList = mutableListOf(DUPLO, BONUS, BALANCE, BALANCE_SENSOR)

    override val actor = AImage(SpriteManager.GameRegion.BONUS.region)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}