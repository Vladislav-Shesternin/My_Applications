package com.csmttt.medus.play.game.box2d.bodies.meduza

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.csmttt.medus.play.game.actors.image.AImage
import com.csmttt.medus.play.game.box2d.bodies.AbstractBody
import com.csmttt.medus.play.game.box2d.bodies.BodyId.*
import com.csmttt.medus.play.game.box2d.bodies.getBodyIdAllE
import com.csmttt.medus.play.game.manager.SpriteManager

class MeduzaBig: AbstractBody() {
    override var id            = MEDUZA
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 1f
        friction = 0.3f
        restitution = 0.5f
    }
    override val collisionList = mutableListOf(*getBodyIdAllE())

    override val actor = AImage(SpriteManager.GameRegion.MEDUZA_BIG.region)



    override fun render(deltaTime: Float) {
        super.render(deltaTime)
//        if (startAnim) {
//
//
//            time += deltaTime
//            actor.drawable = TextureRegionDrawable(anim.getKeyFrame(time))
//
//            if (time >= 1.023f) time = 0f
//        }
//
//        body.apply {
//            setTransform(position, angle + angleValue)
//        }
    }
}