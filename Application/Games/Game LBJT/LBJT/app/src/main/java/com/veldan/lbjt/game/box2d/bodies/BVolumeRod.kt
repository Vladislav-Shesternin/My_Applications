package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.lbjt.game.actors.image.AImage
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup

class BVolumeRod(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: Type
): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.2f
        friction    = 0.2f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType(type))

    fun getActor(): AImage? = actor as? AImage

    fun getRegionByType(type: Type) = when(type) {
        Type.QUIET -> screenBox2d.game.themeUtil.trc.ROD_QUIET
        Type.LOUDER -> screenBox2d.game.themeUtil.trc.ROD_LOUDER
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    enum class Type {
        QUIET, LOUDER
    }
}