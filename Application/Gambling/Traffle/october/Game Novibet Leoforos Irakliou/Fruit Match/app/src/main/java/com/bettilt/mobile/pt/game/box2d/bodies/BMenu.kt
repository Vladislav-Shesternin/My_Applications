package com.bettilt.mobile.pt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bettilt.mobile.pt.game.actors.button.AButton
import com.bettilt.mobile.pt.game.actors.image.AImage
import com.bettilt.mobile.pt.game.box2d.AbstractBody
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedBox2dScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGroup

class BMenu(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "menu"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override var actor: AdvancedGroup? = AButton(screenBox2d, AButton.Type.MENU)

    fun getActor(): AButton = actor as AButton
}