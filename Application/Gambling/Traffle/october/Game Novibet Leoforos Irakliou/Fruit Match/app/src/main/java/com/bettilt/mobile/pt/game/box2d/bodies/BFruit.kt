package com.bettilt.mobile.pt.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.bettilt.mobile.pt.game.actors.AFruit
import com.bettilt.mobile.pt.game.box2d.AbstractBody
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedBox2dScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGroup
import com.bettilt.mobile.pt.game.utils.runGDX

class BFruit(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: Type,
): AbstractBody() {
    override val name       = type.id.toString()
    override val bodyDef    = BodyDef().apply {
        type           = BodyDef.BodyType.DynamicBody
        linearDamping  = 0.4f
        angularDamping = 0.4f
    }
    override val fixtureDef = FixtureDef().apply {
        density     = (1..5).random().toFloat()
        restitution = (10..80).random() / 100f
        friction    = (10..80).random() / 100f
    }
    override var actor: AdvancedGroup? = AFruit(screenBox2d, screenBox2d.game.spriteUtil.ITEM_LIST[type.id.dec()])

    var gluedKey: String? = null
    
    fun getActor(): AFruit = actor as AFruit

    fun freeze() {
        runGDX {
            body?.apply {
                isAwake      = false
                gravityScale = 0f
                setLinearVelocity(0f, 0f)
            }
        }
    }

    fun unfreeze() {
        runGDX {
            body?.apply {
                isAwake      = true
                gravityScale = 1f
            }
        }
    }

    fun destroyFruit(block: () -> Unit) {
        getActor().animBom {
            super.destroy()
            block()
        }
    }


    enum class Type(val id: Int) {
        _1(1), _4(4),
        _2(2), _5(5),
        _3(3), _6(6),
    }

}