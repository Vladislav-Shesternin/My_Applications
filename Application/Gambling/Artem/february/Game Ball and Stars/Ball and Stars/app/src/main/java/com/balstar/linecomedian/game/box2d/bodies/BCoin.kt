package com.balstar.linecomedian.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.balstar.linecomedian.game.actors.image.AImage
import com.balstar.linecomedian.game.box2d.AbstractBody
import com.balstar.linecomedian.game.box2d.BodyId
import com.balstar.linecomedian.game.utils.advanced.AdvancedBox2dScreen
import com.balstar.linecomedian.game.utils.advanced.AdvancedGroup

class BCoin(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply { isSensor = true }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.coin)

    override var id = BodyId.COIN
    override val collisionList = mutableListOf(BodyId.BALL)
}