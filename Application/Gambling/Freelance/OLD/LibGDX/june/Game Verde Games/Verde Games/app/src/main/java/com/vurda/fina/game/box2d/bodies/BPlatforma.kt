package com.vurda.fina.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vurda.fina.game.actors.image.AImage
import com.vurda.fina.game.box2d.AbstractBody
import com.vurda.fina.game.box2d.BodyId
import com.vurda.fina.game.box2d.BodyId.*
import com.vurda.fina.game.manager.SpriteManager
import com.vurda.fina.game.utils.advanced.AdvancedBox2dScreen

class BPlatforma(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = BORDERS
    override val name       = "platforma"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL, GOLD)
    override val actor = AImage(SpriteManager.GameRegion.PLATFORMA.region)
}