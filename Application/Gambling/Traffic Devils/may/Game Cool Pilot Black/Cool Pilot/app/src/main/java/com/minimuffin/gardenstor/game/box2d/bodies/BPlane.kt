package com.minimuffin.gardenstor.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.minimuffin.gardenstor.game.actors.AImage
import com.minimuffin.gardenstor.game.box2d.AbstractBody
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedBox2dScreen
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedGroup

class BPlane(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "plane"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.45f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.assets.pilot)
}