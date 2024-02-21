package com.god.sof.olym.pus.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.god.sof.olym.pus.game.actors.image.AImage
import com.god.sof.olym.pus.game.box2d.AbstractBody
import com.god.sof.olym.pus.game.box2d.BodyId
import com.god.sof.olym.pus.game.utils.advanced.AdvancedBox2dScreen
import com.god.sof.olym.pus.game.utils.advanced.AdvancedGroup

class BOly(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "oly"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.Oly)

    override var id = BodyId.Game.Oly

}