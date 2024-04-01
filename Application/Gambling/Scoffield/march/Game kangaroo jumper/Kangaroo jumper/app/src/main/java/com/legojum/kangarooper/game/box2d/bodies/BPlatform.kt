package com.legojum.kangarooper.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.legojum.kangarooper.game.actors.AImage
import com.legojum.kangarooper.game.box2d.AbstractBody
import com.legojum.kangarooper.game.box2d.BodyId
import com.legojum.kangarooper.game.utils.advanced.AdvancedBox2dScreen
import com.legojum.kangarooper.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BPlatform(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "p"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef()

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.panel)

    override val collisionList = mutableListOf(BodyId.K)

    val atomicBoolean = AtomicBoolean(true)

}