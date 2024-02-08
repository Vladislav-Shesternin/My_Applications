package com.avietor.onlaneslets.game.box2d.bodies

import com.avietor.onlaneslets.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.avietor.onlaneslets.game.box2d.AbstractBody
import com.avietor.onlaneslets.game.box2d.BodyId
import com.avietor.onlaneslets.game.box2d.BodyId.*
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.utils.advanced.AdvancedBox2dScreen

class BBall(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BALL
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BORDERS, STAR, ENEMY)
    override val actor = AImage(SpriteManager.GameRegion.RED.region)
}