package com.csmttt.medus.play.game.box2d.bodies.sikira

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.csmttt.medus.play.game.actors.image.AImage
import com.csmttt.medus.play.game.box2d.bodies.AbstractBody
import com.csmttt.medus.play.game.box2d.bodies.BodyId.*
import com.csmttt.medus.play.game.box2d.bodies.getBodyIdAllE
import com.csmttt.medus.play.game.manager.SpriteManager

class Sikira: AbstractBody() {
    override var id            = WEAPON
    override val name          = "Sikira"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef()
    override val collisionList = mutableListOf(*getBodyIdAllE())

    override val actor = AImage(SpriteManager.GameRegion.SIKIRA.region)
}