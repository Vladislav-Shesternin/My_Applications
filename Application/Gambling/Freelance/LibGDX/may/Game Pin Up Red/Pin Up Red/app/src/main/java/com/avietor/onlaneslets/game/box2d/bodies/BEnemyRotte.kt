package com.avietor.onlaneslets.game.box2d.bodies

import com.avietor.onlaneslets.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.avietor.onlaneslets.game.box2d.AbstractBody
import com.avietor.onlaneslets.game.box2d.BodyId
import com.avietor.onlaneslets.game.box2d.BodyId.*
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.utils.advanced.AdvancedBox2dScreen

class BEnemyRotte(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = ENEMY
    override val name       = "enemy_rotte"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        gravityScale = 0f
    }
    override val fixtureDef = FixtureDef().apply {
        density = 10f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL)
    override val actor = AImage(SpriteManager.GameRegion.ENEMY_ROTTE.region)

    override fun render(deltaTime: Float) {
        super.render(deltaTime)

        body?.applyAngularImpulse(50f, false)
    }
}