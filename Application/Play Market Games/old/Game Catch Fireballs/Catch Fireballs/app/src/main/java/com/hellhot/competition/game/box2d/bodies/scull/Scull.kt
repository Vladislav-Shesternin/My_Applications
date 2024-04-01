package com.hellhot.competition.game.box2d.bodies.scull

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.hellhot.competition.game.actors.image.AImage
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import com.hellhot.competition.game.box2d.bodies.BodyId.*
import com.hellhot.competition.game.manager.SpriteManager
import com.hellhot.competition.game.util.disable

class Scull : AbstractBody() {

    override var id            = SCULL
    override val name          = "Scull"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density     = 1f
        restitution = 0.1f
        friction    = 0.8f
    }
    override val collisionList = mutableListOf(BONUS_1, BONUS_2)

    override val actor = AImage(SpriteManager.GameRegion.SCULL.region).apply { disable() }

    var angleValue = 0f



    override fun render(deltaTime: Float) {
        super.render(deltaTime)
        body.apply {
            setTransform(position, angle + angleValue)
        }
    }
}