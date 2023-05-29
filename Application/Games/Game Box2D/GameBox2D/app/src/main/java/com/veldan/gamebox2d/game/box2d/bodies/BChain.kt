package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.image.AImage
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.BodyId
import com.veldan.gamebox2d.game.box2d.BodyId.*
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BChain(override val screenBox2d: AdvancedBox2dScreen, val color: Color): AbstractBody() {

    override var id         = CHAIN
    override val name       = "chain"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(CAR, BORDERS, CHAIN)
    override val actor = AImage(color.region)


    enum class Color(val region: TextureRegion) {
        BLUE(SpriteManager.GameRegion.BLUE.region),
        RED( SpriteManager.GameRegion.RED.region ),
    }

}