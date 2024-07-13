package com.duckduckmoosedesign.cpk.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.duckduckmoosedesign.cpk.game.actors.AImage
import com.duckduckmoosedesign.cpk.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpk.game.screens.indexPLANE
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedBox2dScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedGroup

class BAvia(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "avia"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.4f
        restitution = 0.4f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.Ser.planes[indexPLANE])

}