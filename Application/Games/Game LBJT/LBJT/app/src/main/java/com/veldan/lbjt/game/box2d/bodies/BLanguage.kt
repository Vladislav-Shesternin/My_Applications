package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.lbjt.game.actors.image.AImage
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.actor.enable
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.runGDX

class BLanguage(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: Type
): AbstractBody() {

    companion object {
        private const val TIME_ANIM_SCALE = 0.7f
    }

    override val name       = "language"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, getLanguageRegionByType(type))

    fun getActor(): AImage? = actor as? AImage

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getLanguageRegionByType(type: Type) = when(type) {
        Type.EN -> screenBox2d.game.themeUtil.assets.LANGUAGE_EN
        Type.UK -> screenBox2d.game.themeUtil.assets.LANGUAGE_UK
    }

    fun decrease(block: () -> Unit) {
        runGDX { getActor()?.apply {
            disable()
            clearActions()
            addAction(Actions.sequence(
                Actions.scaleTo(0.75f, 0.75f, TIME_ANIM_SCALE, Interpolation.swing),
                Actions.run {
                    block()
                    enable()
                },
            ))
        } }
    }

    fun increase(block: () -> Unit) {
        runGDX { getActor()?.apply {
            disable()
            clearActions()
            addAction(Actions.sequence(
                Actions.scaleTo(1.3f, 1.3f, TIME_ANIM_SCALE, Interpolation.swing),
                Actions.run {
                    block()
                    enable()
                },
            ))
        } }
    }

    enum class Type {
        EN, UK
    }
}