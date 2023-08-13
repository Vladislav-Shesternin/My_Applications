package com.veldan.lbjt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.veldan.lbjt.game.actors.button.AButton_Regular
import com.veldan.lbjt.game.actors.volume.AVolume
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen

class BVolume(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: AVolume.Type
): AbstractBody() {
    override val name       = "volume"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override var actor: AdvancedGroup? = AVolume(screenBox2d, type)

    fun getActor(): AVolume? = actor as? AVolume
}