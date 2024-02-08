package com.fortunetiger.carnival.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.fortunetiger.carnival.game.actors.AShar
import com.fortunetiger.carnival.game.box2d.AbstractBody
import com.fortunetiger.carnival.game.box2d.BodyId
import com.fortunetiger.carnival.game.utils.advanced.AdvancedBox2dScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BShar(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "shar"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    private val assets = screenBox2d.game.allAssets

    override var actor: AdvancedGroup? = AShar(screenBox2d)

    // Field
    val atomicBoolean = AtomicBoolean(true)

    private var index = 0

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateImage() {
        index = (0..3).random()
        (actor as AShar).update(assets.shars[index])
    }

    fun updateId() {
        id = BodyId.Game.items[index]
    }

}