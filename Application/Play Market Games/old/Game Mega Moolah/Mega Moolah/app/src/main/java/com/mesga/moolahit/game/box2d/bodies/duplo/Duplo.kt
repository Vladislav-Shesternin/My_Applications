package com.mesga.moolahit.game.box2d.bodies.duplo

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.mesga.moolahit.game.actors.image.AImage
import com.mesga.moolahit.game.box2d.bodies.AbstractBody
import com.mesga.moolahit.game.box2d.bodies.BodyId.BONUS
import com.mesga.moolahit.game.box2d.bodies.BodyId.DUPLO
import com.mesga.moolahit.game.manager.SpriteManager

class Duplo: AbstractBody() {
    override var id            = DUPLO
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        restitution = 0.2f
        friction = 0f
    }
    override val collisionList = mutableListOf(BONUS)

    override val actor = AImage(SpriteManager.GameRegion.DUPLO.region)
}