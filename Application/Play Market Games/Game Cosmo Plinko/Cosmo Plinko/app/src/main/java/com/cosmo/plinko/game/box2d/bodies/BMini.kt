package com.cosmo.plinko.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.cosmo.plinko.game.actors.image.AImage
import com.cosmo.plinko.game.box2d.AbstractBody
import com.cosmo.plinko.game.utils.advanced.AdvancedBox2dScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedGroup

class BMini(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "mini"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.COSMO_BOOTTOM)
}