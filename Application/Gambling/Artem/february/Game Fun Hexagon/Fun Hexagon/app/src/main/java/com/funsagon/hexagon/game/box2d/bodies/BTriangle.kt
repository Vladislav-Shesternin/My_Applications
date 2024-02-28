package com.funsagon.hexagon.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.funsagon.hexagon.game.actors.AHexagon
import com.funsagon.hexagon.game.actors.image.AImage
import com.funsagon.hexagon.game.box2d.AbstractBody
import com.funsagon.hexagon.game.box2d.BodyId
import com.funsagon.hexagon.game.utils.advanced.AdvancedBox2dScreen
import com.funsagon.hexagon.game.utils.advanced.AdvancedGroup

class BTriangle(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "triangle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.triangle)

    override var id = BodyId.TRIANGLE
    override val collisionList = mutableListOf(BodyId.FunWorld.HEXAGON)
}