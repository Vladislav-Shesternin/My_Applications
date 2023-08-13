package com.zeuse.zeusjackpotjamboree.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.zeuse.zeusjackpotjamboree.game.actors.image.AImage
import com.zeuse.zeusjackpotjamboree.game.box2d.AbstractBody
import com.zeuse.zeusjackpotjamboree.game.box2d.BodyId
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedBox2dScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedGroup
import com.zeuse.zeusjackpotjamboree.game.utils.runGDX
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 5f
        friction    = 0.5f
        restitution = 0.8f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d)

    val isTrue = AtomicBoolean(true)
    var score = 0
        private set

    fun updateItem() {
        (actor as? AImage)?.drawable = TextureRegionDrawable(screenBox2d.game.themeUtil.trc.ITEM_LIST.random())
        score = (1..10).random()
    }

    fun freeze() {
        body?.apply {
            runGDX {
                setLinearVelocity(0f, 0f)
                isAwake = false
                gravityScale = 0f
            }
        }
    }

    fun unfreeze() {
        body?.apply {
            runGDX {
                isAwake = true
                gravityScale = 1f
                setLinearVelocity(1f, 1f)
            }
        }
    }

}