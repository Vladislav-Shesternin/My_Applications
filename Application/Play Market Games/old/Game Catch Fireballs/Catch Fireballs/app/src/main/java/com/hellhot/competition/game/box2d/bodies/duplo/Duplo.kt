package com.hellhot.competition.game.box2d.bodies.duplo

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.hellhot.competition.game.actors.image.AImage
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import com.hellhot.competition.game.box2d.bodies.BodyId.*
import com.hellhot.competition.game.manager.SpriteManager
import com.hellhot.competition.game.util.disable
import java.util.*

class Duplo: AbstractBody() {
    override var id            = DUPLO
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 1f
        friction = 1f
    }
    override val collisionList = mutableListOf(BONUS_1, BONUS_2)

    override val actor = AImage(SpriteManager.ListRegion.SHAR.regionList.first()).apply { disable() }

    private val anim = Animation(0.033f, *SpriteManager.ListRegion.SHAR.regionList.toTypedArray()).apply { playMode = Animation.PlayMode.LOOP_PINGPONG }
    private var time = 0f
    private val angleValue = if (Random().nextBoolean()) 0.03f else -0.03f

    var startAnim = false



    override fun render(deltaTime: Float) {
        super.render(deltaTime)
        if (startAnim) {


            time += deltaTime
            actor.drawable = TextureRegionDrawable(anim.getKeyFrame(time))

            if (time >= 1.023f) time = 0f
        }

        body.apply {
            setTransform(position, angle + angleValue)
        }
    }
}