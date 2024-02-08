package com.vbbb.time.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbbb.time.game.game.actors.image.AImage
import com.vbbb.time.game.game.box2d.AbstractBody
import com.vbbb.time.game.game.box2d.BodyId
import com.vbbb.time.game.game.box2d.BodyId.*
import com.vbbb.time.game.game.manager.SpriteManager
import com.vbbb.time.game.game.utils.advanced.AdvancedBox2dScreen

class BBalka(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BORDERS
    override val name       = "balka"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.5f
        friction    = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.BALKA.region)
}