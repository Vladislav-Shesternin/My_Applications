package com.fellinger.yeasman.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.fellinger.yeasman.game.box2d.AbstractBody
import com.fellinger.yeasman.game.box2d.BodyId
import com.fellinger.yeasman.game.box2d.BodyId.Door
import com.fellinger.yeasman.game.box2d.BodyId.Man
import com.fellinger.yeasman.game.box2d.WorldUtil

class Door(override val worldUtil: WorldUtil): AbstractBody() {

    override var id         = Door
    override val name       = "Door"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor = true
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(Man)

}