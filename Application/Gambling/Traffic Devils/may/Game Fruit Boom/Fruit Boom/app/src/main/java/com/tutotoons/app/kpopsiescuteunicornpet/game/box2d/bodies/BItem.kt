package com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.AImage
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.AbstractBody
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.BodyId
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.setPosition
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedBox2dScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.runGDX
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.toUI
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.2f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d)

    private fun getActor(): AImage? = actor as? AImage


    private var particleEffectActor: ParticleEffectActor? = null

    // Field
    private var randomIndex = 0
    var joinGroup = -1

    var boomBlock: () -> Unit = {}

    val isStart = AtomicBoolean(true)

    // Logic ------------------------------------------------------------------------

    fun update() {
        randomIndex = (0..7).random()

        id         = BodyId.ITEM_START
        originalId = BodyId.ItemId.entries[randomIndex].name

        collisionList.addAll(arrayOf(
            BodyId.BORDERS,
            BodyId.ITEM_SENSOR,
            *BodyId.ItemId.entries.map { it.name }.toTypedArray()
        ))

        getActor()?.drawable = TextureRegionDrawable(screenBox2d.game.assetsAll.items[randomIndex])
        particleEffectActor = ParticleEffectActor(ParticleEffect(screenBox2d.game.particleEffectUtil.listBoom[randomIndex]), true)
    }

    fun boom() {
        runGDX {
            startEffect()
            isDestroyActor = false
            joinGroup      = -1
            destroy()
            particleEffectActor?.dispose()

            boomBlock()
        }
    }

    fun addEffect() {
        screenBox2d.stageUI.addActor(particleEffectActor)
    }

    private fun startEffect() {
        particleEffectActor?.apply {
            body?.worldCenter?.toUI?.let { setPosition(it) }
            start()
        }
    }

}