package com.hepagame.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.hepagame.game.actors.AImage
import com.hepagame.game.box2d.AbstractBody
import com.hepagame.game.utils.advanced.AdvancedBox2dScreen
import com.hepagame.game.utils.advanced.AdvancedGroup

class BParamon(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "pash"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
        friction = 0.4f
        restitution = 0.7f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.assasinAll.pardon)

}