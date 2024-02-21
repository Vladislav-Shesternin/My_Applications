package com.jungle.jumping.bird.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.jungle.jumping.bird.game.actors.image.AImage
import com.jungle.jumping.bird.game.box2d.AbstractBody
import com.jungle.jumping.bird.game.box2d.BodyId
import com.jungle.jumping.bird.game.box2d.BodyId.*
import com.jungle.jumping.bird.game.box2d.WorldUtil
import com.jungle.jumping.bird.game.manager.SpriteManager
import com.jungle.jumping.bird.game.utils.to180Y

class Balk(override val worldUtil: WorldUtil, val type: Type): AbstractBody() {

    override var id         = Balk
    override val name       = "Balk"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.KinematicBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(Bird)

    override val actor = AImage(type.region)


    enum class Type(val region: TextureRegion) {
        TOP(SpriteManager.GameRegion.BALK.region),
        BOTTOM(SpriteManager.GameRegion.BALK.region.to180Y),
    }

}