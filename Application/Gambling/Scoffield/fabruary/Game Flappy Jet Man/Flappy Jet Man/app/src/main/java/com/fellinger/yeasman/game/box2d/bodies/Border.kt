package com.fellinger.yeasman.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.fellinger.yeasman.game.actors.image.AImage
import com.fellinger.yeasman.game.box2d.AbstractBody
import com.fellinger.yeasman.game.box2d.BodyId
import com.fellinger.yeasman.game.box2d.WorldUtil
import com.fellinger.yeasman.game.manager.SpriteManager
import com.fellinger.yeasman.game.utils.to180Y

class Border(override val worldUtil: WorldUtil, val type: Type): AbstractBody() {

    override var id         = BodyId.Border
    override val name       = "Border"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val collisionList: MutableList<BodyId> = mutableListOf(BodyId.Man)

    override val actor = AImage(type.region)


    enum class Type(val region: TextureRegion) {
        TOP(SpriteManager.GameRegion.BORDER.region.to180Y),
        BOTTOM(SpriteManager.GameRegion.BORDER.region),
    }

}