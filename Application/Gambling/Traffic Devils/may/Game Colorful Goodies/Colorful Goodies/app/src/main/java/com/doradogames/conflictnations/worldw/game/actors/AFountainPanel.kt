package com.doradogames.conflictnations.worldw.game.actors

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.doradogames.conflictnations.worldw.game.utils.HEIGHT_UI
import com.doradogames.conflictnations.worldw.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.doradogames.conflictnations.worldw.game.utils.actor.disable
import com.doradogames.conflictnations.worldw.game.utils.actor.setBounds
import com.doradogames.conflictnations.worldw.game.utils.actor.setPosition
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedGroup
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedScreen

class AFountainPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val particleActorList = List(6) { AParticleEffectActor(screen, ParticleEffect(screen.game.particleEffectUtil.FOUNTAIN), true) }

    private val positionList = listOf(
        Vector2(203f, HEIGHT_UI),
        Vector2(782f, HEIGHT_UI),
        Vector2(1361f, HEIGHT_UI),

        Vector2(203f, 0f),
        Vector2(782f, 0f),
        Vector2(1361f, 0f),
    )
    private val angleList    = listOf(
        180f, 180f, 180f,
        0f, 0f, 0f
    )

    override fun addActorsOnGroup() {
        disable()

        addActors(particleActorList)

        particleActorList.onEachIndexed { index, aParticleEffectActor->
            aParticleEffectActor.setPosition(positionList[index])
            aParticleEffectActor.setRotation(angleList[index])
            aParticleEffectActor.start()
        }

    }

    fun animHideParticles() {
        particleActorList.take(3).onEach {
            it.addAction(Actions.moveBy(0f, 300f, TIME_ANIM_SCREEN_ALPHA))
        }
        particleActorList.takeLast(3).onEach {
            it.addAction(Actions.moveBy(0f, -300f, TIME_ANIM_SCREEN_ALPHA))
        }
        particleActorList.onEach { it.allowCompletion() }
    }

}