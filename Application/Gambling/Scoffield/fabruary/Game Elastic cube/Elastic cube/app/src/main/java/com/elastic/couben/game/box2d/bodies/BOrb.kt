package com.elastic.couben.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.elastic.couben.game.actors.image.AImage
import com.elastic.couben.game.box2d.AbstractBody
import com.elastic.couben.game.box2d.BodyId
import com.elastic.couben.game.box2d.BodyId.BORDERS
import com.elastic.couben.game.box2d.BodyId.ORB
import com.elastic.couben.game.box2d.bodies.BOrb.Type.GREEN
import com.elastic.couben.game.box2d.bodies.BOrb.Type.RED
import com.elastic.couben.game.manager.SpriteManager
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen

class BOrb(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: Type
): AbstractBody() {
    override var id            = ORB
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 1f
        restitution = 1.05f
    }
    override val collisionList = mutableListOf<BodyId>(BORDERS, ORB)
    override val actor         = AImage(getRegionByType())


    private fun getRegionByType() = when(type) {
        GREEN -> SpriteManager.GameRegion.GREEN.region
        RED   -> SpriteManager.GameRegion.RED.region
    }

    enum class Type {
        GREEN, RED
    }
}