package com.invt.nard.app.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.invt.nard.app.game.actors.image.AImage
import com.invt.nard.app.game.box2d.AbstractBody
import com.invt.nard.app.game.box2d.BodyId
import com.invt.nard.app.game.box2d.BodyId.*
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.advanced.AdvancedBox2dScreen
import com.invt.nard.app.game.utils.advanced.AdvancedGroup

class BBall(override val screenBox2d: AdvancedBox2dScreen, val type: Type): AbstractBody() {
    override var id         = BALL
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = type.data.fixtureDef
    override val collisionList: MutableList<BodyId> = mutableListOf(BORDERS, CIR, BIT)

    override val actor = AImage(type.data.region)


    enum class Type(val data: BallData) {
        _1(
            BallData(SpriteManager.ListRegion.BALL.regionList[0], FixtureDef().apply {
                density     = 2f
                restitution = 0.6f
                friction    = 0.4f
            })
        ),
        _2(
            BallData(SpriteManager.ListRegion.BALL.regionList[1], FixtureDef().apply {
                density     = 1f
                restitution = 0.7f
                friction    = 0.1f
            })
        ),
        _3(
            BallData(SpriteManager.ListRegion.BALL.regionList[2], FixtureDef().apply {
                density     = 10f
                restitution = 0.1f
                friction    = 0.2f
            })
        ),
        _4(
            BallData(SpriteManager.ListRegion.BALL.regionList[3], FixtureDef().apply {
                density     = 0.3f
                restitution = 1f
                friction    = 1f
            })
        ),
        _5(
            BallData(SpriteManager.ListRegion.BALL.regionList[4], FixtureDef().apply {
                density     = 1.5f
                restitution = 0.75f
                friction    = 0.65f
            })
        ),
        _6(
            BallData(SpriteManager.ListRegion.BALL.regionList[5], FixtureDef().apply {
                density     = 5f
                restitution = 0.55f
                friction    = 0.45f
            })
        ),
        _7(
            BallData(SpriteManager.ListRegion.BALL.regionList[6], FixtureDef().apply {
                density     = 4f
                restitution = 0.8f
                friction    = 0.75f
            })
        ),
        _8(
            BallData(SpriteManager.ListRegion.BALL.regionList[7], FixtureDef().apply {
                density     = 3.2f
                restitution = 0.67f
                friction    = 0.47f
            })
        ),
    }

    data class BallData(
        val region: TextureRegion,
        val fixtureDef: FixtureDef,
    )

}