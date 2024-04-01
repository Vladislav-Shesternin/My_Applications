package com.catapult.castles.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.catapult.castles.game.actors.AImage
import com.catapult.castles.game.box2d.AbstractBody
import com.catapult.castles.game.box2d.BodyId
import com.catapult.castles.game.utils.advanced.AdvancedBox2dScreen
import com.catapult.castles.game.utils.advanced.AdvancedGroup

class BLopata(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "lopata"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 12f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.lopata)

    override var id = BodyId.BORDERS
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.CASTLE, BodyId.STONE)

}