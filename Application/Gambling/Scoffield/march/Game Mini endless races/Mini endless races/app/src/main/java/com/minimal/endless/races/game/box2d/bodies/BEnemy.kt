package com.minimal.endless.races.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.minimal.endless.races.game.actors.AImage
import com.minimal.endless.races.game.box2d.AbstractBody
import com.minimal.endless.races.game.box2d.BodyId
import com.minimal.endless.races.game.utils.actor.setPosition
import com.minimal.endless.races.game.utils.advanced.AdvancedBox2dScreen
import com.minimal.endless.races.game.utils.advanced.AdvancedGroup
import com.minimal.endless.races.game.utils.toUI
import java.util.concurrent.atomic.AtomicBoolean

class BEnemy(override val screenBox2d: AdvancedBox2dScreen, private val isLeft: Boolean): AbstractBody() {
    override val name       = "car"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
        restitution = 0.7f
        friction    = 0.2f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, if (isLeft) screenBox2d.game.allAssets.leftCars.random() else screenBox2d.game.allAssets.rightCars.random())

    override var id = BodyId.ENEMY
    override val collisionList = mutableListOf(BodyId.CAR, BodyId.ENEMY)

    // Field
    val atomicBoolean = AtomicBoolean(true)

    private val particleEffect = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.BOOM),false)

    fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffect)
    }

    fun startEffect(position: Vector2) {
        particleEffect.apply {
            setPosition(position)
            start()
        }
    }

}