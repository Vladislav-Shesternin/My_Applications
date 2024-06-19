package com.maxgames.stickwarl.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.maxgames.stickwarl.game.actors.AImage
import com.maxgames.stickwarl.game.actors.AKoleso
import com.maxgames.stickwarl.game.box2d.AbstractBody
import com.maxgames.stickwarl.game.utils.advanced.AdvancedBox2dScreen
import com.maxgames.stickwarl.game.utils.advanced.AdvancedGroup

class BKoleso(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.5f
        restitution = 0.6f
    }

    override var actor: AdvancedGroup? = AKoleso(screenBox2d)

}