package com.vbbb.time.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbbb.time.game.game.actors.image.AImage
import com.vbbb.time.game.game.box2d.AbstractBody
import com.vbbb.time.game.game.box2d.BodyId
import com.vbbb.time.game.game.box2d.BodyId.*
import com.vbbb.time.game.game.manager.SpriteManager
import com.vbbb.time.game.game.utils.actor.disable
import com.vbbb.time.game.game.utils.advanced.AdvancedBox2dScreen

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BALL
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 0.1f
        restitution = 0.6f
        friction    = 0.6f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BORDERS, TARGET, LINE)
    override val actor = AImage(SpriteManager.GameRegion.BALL.region).apply { disable() }
}