package com.orange.magic.board.doodle.color.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.orange.magic.board.doodle.color.game.actors.AImage
import com.orange.magic.board.doodle.color.game.box2d.AbstractBody
import com.orange.magic.board.doodle.color.game.utils.actor.setPosition
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedBox2dScreen
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedGroup
import com.orange.magic.board.doodle.color.game.utils.toUI
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen, val index: Int): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.simagoche.irems[index])

    var isOnStart = AtomicBoolean(true)

    private val particleEffect = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.dwm),false)

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