package com.winterria.jumpilo.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.winterria.jumpilo.game.actors.AImage
import com.winterria.jumpilo.game.box2d.AbstractBody
import com.winterria.jumpilo.game.box2d.BodyId
import com.winterria.jumpilo.game.screens.MenuScreen
import com.winterria.jumpilo.game.utils.advanced.AdvancedBox2dScreen
import com.winterria.jumpilo.game.utils.advanced.AdvancedGroup

class BSnow(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = getSnowId()
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = getSnowDensity()
        restitution = 0.9f
        friction    = 0.7f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, getSnowRegion())

    override var id = BodyId.Winter.SNOW
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.Winter.STAR, BodyId.HILL)

    private fun getSnowRegion(): TextureRegion = when(MenuScreen.indexSnow) {
        0 -> screenBox2d.game.allAssets.snows[0]
        1 -> screenBox2d.game.allAssets.snows[1]
        2 -> screenBox2d.game.allAssets.snows[2]
        3 -> screenBox2d.game.allAssets.snows[3]
        else -> screenBox2d.game.allAssets.snows[0]
    }

    private fun getSnowId(): String = when(MenuScreen.indexSnow) {
        0 -> "snow 1"
        1 -> "snow 2"
        2 -> "snow 3"
        3 -> "snow 4"
        else -> "snow 1"
    }

    private fun getSnowDensity(): Float = when(MenuScreen.indexSnow) {
        0 -> 0.5f
        1 -> 1f
        2 -> 2f
        3 -> 3f
        else -> 0.5f
    }
}