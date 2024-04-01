package com.hellhot.competition.game.box2d.bodies.rightButton

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.hellhot.competition.game.actors.button.AButton
import com.hellhot.competition.game.actors.button.AButtonStyle
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import com.hellhot.competition.game.box2d.bodies.BodyId.*

class RightButton: AbstractBody() {
    override var id            = RIGHT_BUTTON
    override val name          = "RightButton"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        restitution = 0.5f
        friction = 0.7f
    }
    override val collisionList = mutableListOf(BONUS_1, BONUS_2)

    override val actor = AButton(AButtonStyle.right)
}