package com.bottleber.lebeler.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bottleber.lebeler.game.actors.AImage
import com.bottleber.lebeler.game.box2d.AbstractBody
import com.bottleber.lebeler.game.box2d.BodyId
import com.bottleber.lebeler.game.utils.advanced.AdvancedBox2dScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedGroup

class BPricel(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        gravityScale  = 0f
        fixedRotation = true
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.precel)

    override var id = BodyId.PRICEL
    override val collisionList = mutableListOf(BodyId.PRICEL_BOX, BodyId.PRICEL_BOX_DOWN)

}