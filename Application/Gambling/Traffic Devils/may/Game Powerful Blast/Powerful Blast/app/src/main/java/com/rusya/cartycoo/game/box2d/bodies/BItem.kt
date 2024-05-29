package com.rusya.cartycoo.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.rusya.cartycoo.game.actors.AImage
import com.rusya.cartycoo.game.box2d.AbstractBody
import com.rusya.cartycoo.game.screens.SetsScreen
import com.rusya.cartycoo.game.utils.actor.setPosition
import com.rusya.cartycoo.game.utils.advanced.AdvancedBox2dScreen
import com.rusya.cartycoo.game.utils.advanced.AdvancedGroup
import com.rusya.cartycoo.game.utils.toUI
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen, val index: Int): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    private val confetiList = if (SetsScreen.isCandy) screenBox2d.game.faradeo.confeti.take(5) + screenBox2d.game.faradeo.confeti.take(5)
                              else screenBox2d.game.faradeo.confeti.takeLast(5) + screenBox2d.game.faradeo.confeti.takeLast(5)

    override var actor: AdvancedGroup? = AImage(screenBox2d, confetiList[index])

    var isOnStart = AtomicBoolean(true)

    val IDID = if (index<5) index else index-5


    private val particleEffect = if (SetsScreen.isCandy) ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.CandyBoom),false)
                                 else ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.FruitBoom),false)

    private fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffect)
    }

    fun startEffect() {
        particleEffect.apply {
            setPosition(body!!.position.toUI)
            start()
        }
    }

    init {
        addEffect()
    }

}