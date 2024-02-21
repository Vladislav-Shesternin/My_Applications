package com.fellinger.yeasman.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.fellinger.yeasman.game.box2d.AbstractBody
import com.fellinger.yeasman.game.box2d.BodyId
import com.fellinger.yeasman.game.box2d.BodyId.*
import com.fellinger.yeasman.game.box2d.WorldUtil

class Man(override val worldUtil: WorldUtil): AbstractBody() {

    override var id         = Man
    override val name       = "Man"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 2f
    }
    override val collisionList: MutableList<BodyId> = mutableListOf(Border, Balk, Door)

}