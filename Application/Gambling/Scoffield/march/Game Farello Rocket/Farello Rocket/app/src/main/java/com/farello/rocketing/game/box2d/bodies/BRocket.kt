package com.farello.rocketing.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.farello.rocketing.game.actors.AImage
import com.farello.rocketing.game.box2d.AbstractBody
import com.farello.rocketing.game.box2d.BodyId
import com.farello.rocketing.game.utils.actor.setPosition
import com.farello.rocketing.game.utils.advanced.AdvancedBox2dScreen
import com.farello.rocketing.game.utils.advanced.AdvancedGroup
import com.farello.rocketing.game.utils.toUI

class BRocket(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "rocket"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.55f
        friction    = 0.25f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.farello)

    override var id = BodyId.ROCKET
    override val collisionList = mutableListOf(BodyId.ASTEROID)

    private val particleEffect = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.ROCKET),false)

    override fun onCreate() {
        addEffect()
    }

    private fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffect)
        particleEffect.start()
    }

    override fun render(deltaTime: Float) {
        super.render(deltaTime)
        particleEffect.setPosition(body!!.position.cpy().toUI.add(60f, 65f))
    }

}