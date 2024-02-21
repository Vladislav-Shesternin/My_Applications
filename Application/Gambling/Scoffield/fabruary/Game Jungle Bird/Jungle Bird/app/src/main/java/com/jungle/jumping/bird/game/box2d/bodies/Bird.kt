package com.jungle.jumping.bird.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jungle.jumping.bird.game.actors.image.AImage
import com.jungle.jumping.bird.game.box2d.AbstractBody
import com.jungle.jumping.bird.game.box2d.BodyId
import com.jungle.jumping.bird.game.box2d.BodyId.*
import com.jungle.jumping.bird.game.box2d.WorldUtil
import com.jungle.jumping.bird.game.manager.SpriteManager

class Bird(override val worldUtil: WorldUtil): AbstractBody() {

    override var id         = Bird
    override val name       = "Bird"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 3f
        restitution = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(Border, Balk, Door)

    override val actor = AImage(SpriteManager.GameRegion.BIRD.region)

}