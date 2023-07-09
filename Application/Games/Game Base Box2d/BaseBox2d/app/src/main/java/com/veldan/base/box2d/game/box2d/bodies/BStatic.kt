package com.veldan.base.box2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.base.box2d.game.actors.image.AImage
import com.veldan.base.box2d.game.box2d.AbstractBody
import com.veldan.base.box2d.game.manager.SpriteManager
import com.veldan.base.box2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.base.box2d.game.utils.advanced.AdvancedGroup

class BStatic(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override var actor: AdvancedGroup? = AImage(SpriteManager.GameRegion.YAN.region)
}