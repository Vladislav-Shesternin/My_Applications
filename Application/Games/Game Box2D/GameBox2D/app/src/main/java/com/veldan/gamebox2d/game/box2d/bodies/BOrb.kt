package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.image.AImage
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.BodyId
import com.veldan.gamebox2d.game.box2d.BodyId.*
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.BOrb.Type.GREEN
import com.veldan.gamebox2d.game.box2d.bodies.BOrb.Type.RED
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

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
        restitution = 0.7f
    }
    override val collisionList = mutableListOf<BodyId>(BORDERS)
    override val actor         = AImage(getRegionByType())


    private fun getRegionByType() = when(type) {
        GREEN -> SpriteManager.GameRegion.GREEN.region
        RED   -> SpriteManager.GameRegion.RED.region
    }

    enum class Type {
        GREEN, RED
    }
}