package com.picadilla.beepbeepvroo.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.picadilla.beepbeepvroo.game.actors.AImage
import com.picadilla.beepbeepvroo.game.box2d.AbstractBody
import com.picadilla.beepbeepvroo.game.utils.advanced.AdvancedBox2dScreen
import com.picadilla.beepbeepvroo.game.utils.advanced.AdvancedGroup

class BMal(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.simagoche.malchonka)
}