package com.legojum.kangarooper.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.legojum.kangarooper.game.actors.AImage
import com.legojum.kangarooper.game.box2d.AbstractBody
import com.legojum.kangarooper.game.box2d.BodyId
import com.legojum.kangarooper.game.utils.advanced.AdvancedBox2dScreen
import com.legojum.kangarooper.game.utils.advanced.AdvancedGroup

class BUser(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density  = 100f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.control)

    override var id = BodyId.USER

}