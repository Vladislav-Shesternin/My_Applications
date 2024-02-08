package com.vbbb.time.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbbb.time.game.game.actors.image.AImage
import com.vbbb.time.game.game.box2d.AbstractBody
import com.vbbb.time.game.game.box2d.BodyId
import com.vbbb.time.game.game.box2d.BodyId.*
import com.vbbb.time.game.game.manager.SpriteManager
import com.vbbb.time.game.game.utils.advanced.AdvancedBox2dScreen

class BLine(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = LINE
    override val name       = "line"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor = true
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    //override val actor = AImage(SpriteManager.GameRegion.BALL.region)
}