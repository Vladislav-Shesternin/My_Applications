package com.jumping.cubuletus.game.box2d.bodies.batut

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jumping.cubuletus.game.actors.image.AImage
import com.jumping.cubuletus.game.box2d.bodies.AbstractBody
import com.jumping.cubuletus.game.box2d.bodies.BodyId.*
import com.jumping.cubuletus.game.manager.SpriteManager

class BBatut: AbstractBody() {
    override var id            = BATUT
    override val name          = "Batut"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        friction    = 0.5f
        restitution = 1f
    }
    override val collisionList = mutableListOf(COUB)

    override val actor = AImage(SpriteManager.GameRegion.BATUT.region)

}