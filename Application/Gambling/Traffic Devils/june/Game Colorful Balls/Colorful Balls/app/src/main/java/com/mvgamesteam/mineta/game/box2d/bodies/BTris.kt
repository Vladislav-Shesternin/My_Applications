package com.mvgamesteam.mineta.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.mvgamesteam.mineta.game.actors.AImage
import com.mvgamesteam.mineta.game.box2d.AbstractBody
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedBox2dScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedGroup

class BTris(override val screenBox2d: AdvancedBox2dScreen, index: Int): AbstractBody() {
    override val name       = "tro"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        friction    = 0.3f
        restitution = 0.3f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.Jer.titr)

}