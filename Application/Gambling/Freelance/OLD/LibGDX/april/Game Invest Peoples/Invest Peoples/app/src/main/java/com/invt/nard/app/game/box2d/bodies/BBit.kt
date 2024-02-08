package com.invt.nard.app.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.invt.nard.app.game.actors.image.AImage
import com.invt.nard.app.game.box2d.AbstractBody
import com.invt.nard.app.game.box2d.BodyId
import com.invt.nard.app.game.box2d.BodyId.*
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.advanced.AdvancedBox2dScreen
import com.invt.nard.app.game.utils.advanced.AdvancedGroup

class BBit(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override var id         = BIT
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        //density = 100f
        restitution = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.BITCOIN.region)
}