package com.elastices.platferma.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.elastices.platferma.game.box2d.AbstractBody
import com.elastices.platferma.game.box2d.BodyId
import com.elastices.platferma.game.box2d.BodyId.*
import com.elastices.platferma.game.utils.advanced.AdvancedBox2dScreen

class BRec(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = REC
    override val name       = "rec"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        friction = 0f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(PANEL)
}