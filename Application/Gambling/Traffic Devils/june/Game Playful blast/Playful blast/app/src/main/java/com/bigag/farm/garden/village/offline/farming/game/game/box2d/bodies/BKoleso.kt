package com.bigag.farm.garden.village.offline.farming.game.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bigag.farm.garden.village.offline.farming.game.game.actors.AKoleso
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.AbstractBody
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedBox2dScreen
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup

class BKoleso(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        friction    = 0.5f
        restitution = 0.6f
    }

    override var actor: com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup? = AKoleso(screenBox2d)

}