package com.jumping.cubuletus.game.box2d.bodies.coin

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jumping.cubuletus.game.actors.image.AImage
import com.jumping.cubuletus.game.box2d.bodies.AbstractBody
import com.jumping.cubuletus.game.box2d.bodies.BodyId.*
import com.jumping.cubuletus.game.manager.SpriteManager

class BCoin: AbstractBody() {
    override var id            = COIN
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef()
    override val collisionList = mutableListOf(COUB)

    override val actor = AImage(SpriteManager.GameRegion.COIN.region)

}