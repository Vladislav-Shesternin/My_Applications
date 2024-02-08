package com.vurda.fina.game.box2d.bodies

import com.vurda.fina.game.actors.image.AImage
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vurda.fina.game.box2d.AbstractBody
import com.vurda.fina.game.box2d.BodyId
import com.vurda.fina.game.box2d.BodyId.*
import com.vurda.fina.game.manager.SpriteManager
import com.vurda.fina.game.utils.advanced.AdvancedBox2dScreen

class BGold(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {

    override var id         = GOLD
    override val name       = "gold"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(BALL, BORDERS)
    override val actor = AImage(SpriteManager.GameRegion.CUBOK_E.region)
}