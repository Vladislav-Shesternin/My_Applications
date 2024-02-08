package com.vbtb.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbtb.game.game.actors.image.AImage
import com.vbtb.game.game.box2d.AbstractBody
import com.vbtb.game.game.box2d.BodyId
import com.vbtb.game.game.box2d.BodyId.*
import com.vbtb.game.game.manager.SpriteManager
import com.vbtb.game.game.utils.advanced.AdvancedBox2dScreen

class BBlock(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BORDERS
    override val name       = "block"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(ORBIK, GOLD)
    override val actor = AImage(SpriteManager.GameRegion.BOTTOM.region)
}