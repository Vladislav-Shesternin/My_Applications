package com.doradogames.conflictnations.worldwar.game.box2d.bodies.standart

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.doradogames.conflictnations.worldwar.game.box2d.AbstractBody
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedBox2dScreen

class BKinematic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef()
}