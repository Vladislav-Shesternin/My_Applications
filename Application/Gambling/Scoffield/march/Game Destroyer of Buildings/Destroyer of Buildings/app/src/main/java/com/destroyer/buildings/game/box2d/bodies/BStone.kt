package com.destroyer.buildings.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.destroyer.buildings.game.actors.AImage
import com.destroyer.buildings.game.box2d.AbstractBody
import com.destroyer.buildings.game.box2d.BodyId
import com.destroyer.buildings.game.utils.actor.setPosition
import com.destroyer.buildings.game.utils.advanced.AdvancedBox2dScreen
import com.destroyer.buildings.game.utils.advanced.AdvancedGroup
import com.destroyer.buildings.game.utils.currentTimeMinus
import com.destroyer.buildings.game.utils.toUI

class BStone(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 0.8f
        restitution = 0.1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.stone)

    override var id = BodyId.STONE
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.BUILD)

    private val particleEffect = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.BOOM),false)


    private fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffect)
    }

    private fun startEffect() {
        particleEffect.apply {
            setPosition(body!!.position.toUI)
            start()
        }
    }

    init {
        var timeContact = 0L

        addEffect()

        beginContactBlockArray.add(ContactBlock { body, _ ->
            if (body.id == BodyId.BUILD) {
                if (currentTimeMinus(timeContact) >= 1000) {
                    startEffect()

                    screenBox2d.game.soundUtil.apply { play(PUNCH, 50f) }
                    timeContact = System.currentTimeMillis()
                }
            }

        })
    }


}