package com.elastic.couben.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.elastic.couben.game.actors.image.AImage
import com.elastic.couben.game.box2d.AbstractBody
import com.elastic.couben.game.box2d.BodyId
import com.elastic.couben.game.box2d.BodyId.*
import com.elastic.couben.game.manager.SpriteManager
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen

class BBorders(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BORDERS
    override val name       = "Borders"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(ORB, MOMKA)
    override val actor = AImage(SpriteManager.GameRegion.BORDERS.region)
}