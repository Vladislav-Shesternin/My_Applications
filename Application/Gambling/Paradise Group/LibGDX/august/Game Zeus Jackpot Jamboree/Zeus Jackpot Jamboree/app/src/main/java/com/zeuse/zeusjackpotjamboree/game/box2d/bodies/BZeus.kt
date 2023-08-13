package com.zeuse.zeusjackpotjamboree.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.zeuse.zeusjackpotjamboree.game.actors.image.AImage
import com.zeuse.zeusjackpotjamboree.game.box2d.AbstractBody
import com.zeuse.zeusjackpotjamboree.game.box2d.BodyId
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedBox2dScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedGroup

class BZeus(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "zeus"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.5f
        restitution = 0.4f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.themeUtil.trc.ZEUS)
}