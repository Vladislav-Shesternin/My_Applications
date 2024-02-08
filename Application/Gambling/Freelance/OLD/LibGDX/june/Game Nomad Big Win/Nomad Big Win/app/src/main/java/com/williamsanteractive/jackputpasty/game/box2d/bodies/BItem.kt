package com.williamsanteractive.jackputpasty.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.williamsanteractive.jackputpasty.game.actors.image.AImage
import com.williamsanteractive.jackputpasty.game.box2d.AbstractBody
import com.williamsanteractive.jackputpasty.game.box2d.BodyId
import com.williamsanteractive.jackputpasty.game.box2d.BodyId.*
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager
import com.williamsanteractive.jackputpasty.game.utils.Size
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedBox2dScreen
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGroup

class BItem(override val screenBox2d: AdvancedBox2dScreen, val type: Typ): AbstractBody() {

    override var id         = ITEM
    override val name       = type.nam
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
        restitution = 0.3f
        friction = 0.5f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(ITEM, CIRC, BIT, ENEMY)
    override val actor = AImage(type.reg)



    enum class Typ(
        val nam: String,
        val reg: TextureRegion,
        val size: Size,
    ) {
        _1("zmia", SpriteManager.ListRegion.ITEMKES.regionList[0], Size(51f, 69f)),
        _2("cube", SpriteManager.ListRegion.ITEMKES.regionList[1], Size(84f, 84f)),
        _3("circle", SpriteManager.ListRegion.ITEMKES.regionList[2], Size(63f, 63f)),
        _4("oval", SpriteManager.ListRegion.ITEMKES.regionList[3], Size(70f, 67f)),
        _5("circle", SpriteManager.ListRegion.ITEMKES.regionList[4], Size(63f, 63f)),
    }
}