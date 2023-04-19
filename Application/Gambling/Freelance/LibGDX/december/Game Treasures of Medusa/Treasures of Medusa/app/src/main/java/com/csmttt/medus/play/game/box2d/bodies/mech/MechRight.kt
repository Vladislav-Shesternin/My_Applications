package com.csmttt.medus.play.game.box2d.bodies.mech

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.csmttt.medus.play.game.actors.image.AImage
import com.csmttt.medus.play.game.box2d.bodies.AbstractBody
import com.csmttt.medus.play.game.box2d.bodies.BodyId.*
import com.csmttt.medus.play.game.box2d.bodies.getBodyIdAllE
import com.csmttt.medus.play.game.manager.SpriteManager

class MechRight : AbstractBody() {

    override var id            = WEAPON
    override val name          = "mech_r"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        friction    = 0.6f
        restitution = 0.4f
    }
    override val collisionList = mutableListOf(*getBodyIdAllE())

    override val actor = AImage(SpriteManager.GameRegion.MECH_R.region)

}