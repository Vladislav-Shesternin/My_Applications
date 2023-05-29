package com.avietor.onlaneslets.game.box2d.bodies

import com.avietor.onlaneslets.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.avietor.onlaneslets.game.box2d.AbstractBody
import com.avietor.onlaneslets.game.box2d.BodyId
import com.avietor.onlaneslets.game.box2d.BodyId.*
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.utils.advanced.AdvancedBox2dScreen

class BHor(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BORDERS
    override val name       = "top"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.TOP.region)
}