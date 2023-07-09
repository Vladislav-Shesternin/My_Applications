package com.williamsanteractive.jackputpasty.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.williamsanteractive.jackputpasty.game.box2d.AbstractBody
import com.williamsanteractive.jackputpasty.game.box2d.BodyId
import com.williamsanteractive.jackputpasty.game.box2d.BodyId.*
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedBox2dScreen

class BBit(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BIT
    override val name       = "bit"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(ITEM)

    var cost = 0

}