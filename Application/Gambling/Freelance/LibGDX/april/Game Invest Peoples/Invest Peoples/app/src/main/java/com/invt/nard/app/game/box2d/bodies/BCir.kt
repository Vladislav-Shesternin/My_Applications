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

class BCir(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override var id         = CIR
    override val name       = "cir"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        restitution = 0.7f
        friction    = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.GREEN.region)

    private var counter = 0

    override fun endContact(contactBody: AbstractBody) {
        counter++
        if (counter % 2 == 0) actor.drawable = TextureRegionDrawable(SpriteManager.GameRegion.GREEN.region)
        else actor.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BLUE.region)
        super.endContact(contactBody)
    }
}