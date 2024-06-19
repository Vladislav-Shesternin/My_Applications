package com.doradogames.conflictnations.worldw.game.box2d.bodies.standart

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.doradogames.conflictnations.worldw.game.box2d.AbstractBody
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedBox2dScreen

class BDynamic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
}