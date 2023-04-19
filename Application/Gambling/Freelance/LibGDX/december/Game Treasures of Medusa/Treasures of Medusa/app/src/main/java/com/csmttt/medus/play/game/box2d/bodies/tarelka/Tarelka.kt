package com.csmttt.medus.play.game.box2d.bodies.tarelka

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.csmttt.medus.play.game.actors.image.AImage
import com.csmttt.medus.play.game.box2d.bodies.AbstractBody
import com.csmttt.medus.play.game.box2d.bodies.BodyId.*
import com.csmttt.medus.play.game.box2d.bodies.getBodyIdAllE
import com.csmttt.medus.play.game.manager.SpriteManager
import com.csmttt.medus.play.game.util.disable

class Tarelka: AbstractBody() {
    override var id            = TARE
    override val name          = "Tarelka"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef()
    override val collisionList = mutableListOf(*getBodyIdAllE())

    override val actor = AImage(SpriteManager.GameRegion.TARELKA.region).apply { disable() }

}