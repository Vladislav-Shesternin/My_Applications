package com.country.birds.game.box2d.bodies.standart

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.country.birds.game.actors.image.AImage
import com.country.birds.game.box2d.AbstractBody
import com.country.birds.game.utils.advanced.AdvancedBox2dScreen
import com.country.birds.game.utils.advanced.AdvancedGroup

class BKinematic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef()
}