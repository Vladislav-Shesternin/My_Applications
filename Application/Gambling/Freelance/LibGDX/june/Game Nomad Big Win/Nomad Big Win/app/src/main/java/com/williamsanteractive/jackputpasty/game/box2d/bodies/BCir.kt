package com.williamsanteractive.jackputpasty.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.williamsanteractive.jackputpasty.game.actors.image.AImage
import com.williamsanteractive.jackputpasty.game.box2d.AbstractBody
import com.williamsanteractive.jackputpasty.game.box2d.BodyId
import com.williamsanteractive.jackputpasty.game.box2d.BodyId.*
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedBox2dScreen
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGroup

class BCir(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = CIRC
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 2f
        restitution = 0.3f
        friction = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(ITEM)
    override val actor = AImage(SpriteManager.GameRegion.CIRIL.region)
}