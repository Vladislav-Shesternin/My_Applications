package com.vbtb.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbtb.game.game.actors.image.AImage
import com.vbtb.game.game.box2d.AbstractBody
import com.vbtb.game.game.box2d.BodyId
import com.vbtb.game.game.box2d.BodyId.*
import com.vbtb.game.game.manager.SpriteManager
import com.vbtb.game.game.utils.advanced.AdvancedBox2dScreen

class BBowl(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = ORBIK
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BORDERS, GOLD)
    override val actor = AImage(SpriteManager.GameRegion.BOWL.region)
}