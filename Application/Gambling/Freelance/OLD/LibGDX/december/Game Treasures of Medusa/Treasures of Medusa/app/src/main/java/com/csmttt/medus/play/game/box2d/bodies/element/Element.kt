package com.csmttt.medus.play.game.box2d.bodies.element

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.csmttt.medus.play.game.actors.image.AImage
import com.csmttt.medus.play.game.box2d.bodies.AbstractBody
import com.csmttt.medus.play.game.box2d.bodies.BodyId.*
import com.csmttt.medus.play.game.box2d.bodies.getBodyIdAllE
import com.csmttt.medus.play.game.manager.SpriteManager

class Element(val data: Data): AbstractBody() {
    override var id            = NONE
    override val name          = data._name
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 1f
    }
    override val collisionList = mutableListOf(WEAPON, TARE, MEDUZA, *getBodyIdAllE())

    override val actor = AImage(data.region)

    var originId = id



    enum class Data(val _name: String, val region: TextureRegion) {
        _1("1", SpriteManager.ListRegion.ELEMENTS.regionList[0]),
        _2("2", SpriteManager.ListRegion.ELEMENTS.regionList[1]),
        _3("3", SpriteManager.ListRegion.ELEMENTS.regionList[2]),
        _4("4", SpriteManager.ListRegion.ELEMENTS.regionList[3]),
        _5("5", SpriteManager.ListRegion.ELEMENTS.regionList[4]),
        _6("6", SpriteManager.ListRegion.ELEMENTS.regionList[5]),
        _7("7", SpriteManager.ListRegion.ELEMENTS.regionList[6]),
        _8("8", SpriteManager.ListRegion.ELEMENTS.regionList[7]),
    }

}