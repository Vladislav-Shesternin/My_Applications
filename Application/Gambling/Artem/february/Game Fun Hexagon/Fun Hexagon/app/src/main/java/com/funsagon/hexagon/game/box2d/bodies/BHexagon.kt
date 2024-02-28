package com.funsagon.hexagon.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.funsagon.hexagon.game.actors.AHexagon
import com.funsagon.hexagon.game.box2d.AbstractBody
import com.funsagon.hexagon.game.box2d.BodyId
import com.funsagon.hexagon.game.utils.advanced.AdvancedBox2dScreen
import com.funsagon.hexagon.game.utils.advanced.AdvancedGroup

class BHexagon(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "hexagon"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        restitution = 0.9f
        friction    = 0.6f
    }

    override var actor: AdvancedGroup? = AHexagon(screenBox2d)

    override var id = BodyId.FunWorld.HEXAGON
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.DOWN, BodyId.FunWorld.STAR, BodyId.TRIANGLE)
}