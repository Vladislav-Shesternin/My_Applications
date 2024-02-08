package com.invt.nard.app.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.invt.nard.app.game.actors.image.AImage
import com.invt.nard.app.game.box2d.AbstractBody
import com.invt.nard.app.game.box2d.BodyId
import com.invt.nard.app.game.box2d.BodyId.*
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.advanced.AdvancedBox2dScreen

class BVer(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override var id         = BORDERS
    override val name       = "ver"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
}