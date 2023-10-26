package com.bettilt.mobile.pt.game.box2d

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.bettilt.mobile.pt.game.manager.util.SoundUtil
import com.bettilt.mobile.pt.util.currentTimeMinus

class WorldContactListener: ContactListener {

    private var timeContactStatic  = 0L

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as AbstractBody)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as AbstractBody)

    private val tmpArray = Array<AbstractBody>().apply { setSize(2) }
    private lateinit var soundUtil: SoundUtil

    var beginContactBlockArray = Array<ContactBlock>()
    var endContactBlockArray   = Array<ContactBlock>()

    override fun beginContact(contact: Contact) {
        with(contact) {
            playSound(abstractBodyA, abstractBodyB)
            abstractBodyA.beginContact(abstractBodyB)
            abstractBodyB.beginContact(abstractBodyA)
            beginContactBlockArray.onEach { it.block(abstractBodyA, abstractBodyB) }
        }
    }

    override fun endContact(contact: Contact) {
        with(contact) {
            abstractBodyA.endContact(abstractBodyB)
            abstractBodyB.endContact(abstractBodyA)
            endContactBlockArray.onEach { it.block(abstractBodyA, abstractBodyB) }
        }
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {}

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {}

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun playSound(bodyA: AbstractBody, bodyB: AbstractBody) {
        tmpArray[0] = bodyA
        tmpArray[1] = bodyB

        soundUtil = bodyA.screenBox2d.game.soundUtil

        if (tmpArray.all { it.fixtureDef.isSensor.not() }) {
            when {
                tmpArray.any { it.id == BodyId.BORDERS }
                -> {}

                tmpArray.any { it.bodyDef.type == BodyDef.BodyType.StaticBody }
                -> if (currentTimeMinus(timeContactStatic) >= 200) {
                    soundUtil.apply { play(CLUNK) }
                    timeContactStatic = System.currentTimeMillis()
                }
            }
        }

    }

    // ---------------------------------------------------
    // SAM
    // ---------------------------------------------------

    fun interface ContactBlock { fun block(bodyA: AbstractBody, bodyB: AbstractBody) }

}