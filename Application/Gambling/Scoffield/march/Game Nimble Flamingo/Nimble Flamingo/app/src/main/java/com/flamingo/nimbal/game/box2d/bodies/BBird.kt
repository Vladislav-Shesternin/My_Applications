package com.flamingo.nimbal.game.box2d.bodies

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.flamingo.nimbal.game.actors.AImageAnim
import com.flamingo.nimbal.game.box2d.AbstractBody
import com.flamingo.nimbal.game.box2d.BodyId
import com.flamingo.nimbal.game.utils.advanced.AdvancedBox2dScreen
import com.flamingo.nimbal.game.utils.advanced.AdvancedGroup

class BBird(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        fixedRotation = true
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImageAnim(screenBox2d, 0.2f, screenBox2d.game.allAssets.birds, Animation.PlayMode.LOOP)

    override var id            = BodyId.Coyntry.BIRD
    override val collisionList = mutableListOf(BodyId.BORDERS, BodyId.Coyntry.WIN)

}