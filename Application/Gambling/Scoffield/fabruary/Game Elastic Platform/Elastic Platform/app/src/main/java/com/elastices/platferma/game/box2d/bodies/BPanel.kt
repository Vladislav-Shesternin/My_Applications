package com.elastices.platferma.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.elastices.platferma.game.actors.image.AImage
import com.elastices.platferma.game.box2d.AbstractBody
import com.elastices.platferma.game.box2d.BodyId
import com.elastices.platferma.game.box2d.BodyId.*
import com.elastices.platferma.game.manager.SpriteManager
import com.elastices.platferma.game.utils.advanced.AdvancedBox2dScreen

class BPanel(override val screenBox2d: AdvancedBox2dScreen, ): AbstractBody() {
    override var id            = PANEL
    override val name          = "panel"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 5f
        restitution = 0.7f
        friction = 0.3f
    }
    override val collisionList = mutableListOf<BodyId>(BORDERS, ORB, REC)
    override val actor         = AImage(SpriteManager.GameRegion.PANEL.region)

}