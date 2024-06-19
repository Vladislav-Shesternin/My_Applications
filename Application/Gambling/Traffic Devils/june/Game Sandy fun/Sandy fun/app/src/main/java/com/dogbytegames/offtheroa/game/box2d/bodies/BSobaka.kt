package com.dogbytegames.offtheroa.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.dogbytegames.offtheroa.game.actors.AImage
import com.dogbytegames.offtheroa.game.box2d.AbstractBody
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedBox2dScreen
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedGroup

class BSobaka(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.5f
        restitution = 0.6f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.aAlibaba.nubik)

}