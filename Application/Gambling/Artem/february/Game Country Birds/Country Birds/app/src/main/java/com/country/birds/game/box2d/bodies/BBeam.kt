package com.country.birds.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.country.birds.game.actors.image.AImage
import com.country.birds.game.actors.image.AImageAnim
import com.country.birds.game.box2d.AbstractBody
import com.country.birds.game.box2d.BodyId
import com.country.birds.game.utils.advanced.AdvancedBox2dScreen
import com.country.birds.game.utils.advanced.AdvancedGroup

class BBeam(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "beam"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        fixedRotation = true
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.allAssets.beam)

    override var id            = BodyId.BORDERS
    override val collisionList = mutableListOf(BodyId.Coyntry.BIRD)

}