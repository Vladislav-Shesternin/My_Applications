package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.image.AImage
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.BodyId
import com.veldan.gamebox2d.game.box2d.BodyId.*
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BCar(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = CAR
    override val name       = "car"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(BORDERS, WHEEL)
    override val actor = AImage(SpriteManager.GameRegion.CAR.region)
}