package com.hellhot.competition.game.box2d.bodies.tare

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.hellhot.competition.game.actors.image.AImage
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import com.hellhot.competition.game.box2d.bodies.BodyId.*
import com.hellhot.competition.game.manager.SpriteManager
import com.hellhot.competition.game.util.disable

class Tare: AbstractBody() {
    override var id            = TARE
    override val name          = "Tare"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef()
    override val collisionList = mutableListOf(BONUS_1, BONUS_2)

    override val actor = AImage(SpriteManager.GameRegion.TARE.region).apply { disable() }

}