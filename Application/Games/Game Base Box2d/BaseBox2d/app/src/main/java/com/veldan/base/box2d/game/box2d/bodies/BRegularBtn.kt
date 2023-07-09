package com.veldan.base.box2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.veldan.base.box2d.game.actors.button.ARegularButton
import com.veldan.base.box2d.game.box2d.AbstractBody
import com.veldan.base.box2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.base.box2d.game.utils.advanced.AdvancedGroup

class BRegularBtn(
    override val screenBox2d: AdvancedBox2dScreen,
    val text : String,
    val style: LabelStyle,
): AbstractBody() {
    override val name       = "regular_btn"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.3f
    }
    override var actor: AdvancedGroup? = ARegularButton(text, style)
}