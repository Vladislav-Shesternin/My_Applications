package com.hellhot.competition.game.box2d.bodies.bonus

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.hellhot.competition.game.actors.image.AImage
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import com.hellhot.competition.game.box2d.bodies.BodyId.*
import com.hellhot.competition.game.util.disable

class Bonus : AbstractBody() {

    override var id            = NONE
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.6f
    }
    override val collisionList = mutableListOf(DUPLO, BONUS_1, BONUS_2, TARE, LEFT_BUTTON, RIGHT_BUTTON, SCULL)

    override val actor = AImage().apply { disable() }

    var originId = NONE

}