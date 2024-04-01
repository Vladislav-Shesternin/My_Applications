package com.guardians.galaxyguano.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.guardians.galaxyguano.game.actors.AImage
import com.guardians.galaxyguano.game.box2d.AbstractBody
import com.guardians.galaxyguano.game.box2d.BodyId
import com.guardians.galaxyguano.game.utils.actor.setPosition
import com.guardians.galaxyguano.game.utils.advanced.AdvancedBox2dScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedGroup
import com.guardians.galaxyguano.game.utils.toUI
import java.util.concurrent.atomic.AtomicBoolean

class BAsteroid(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.asteroids.random())

    override var id = BodyId.ASTEROID
    override val collisionList = mutableListOf(BodyId.BALL)

    // Field
    val atomicBoolean = AtomicBoolean(true)

    private val particleEffect = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.ASTEROID),false)

    fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffect)
    }

    fun startEffect() {
        particleEffect.apply {
            setPosition(body!!.position.toUI)
            start()
        }
    }

}