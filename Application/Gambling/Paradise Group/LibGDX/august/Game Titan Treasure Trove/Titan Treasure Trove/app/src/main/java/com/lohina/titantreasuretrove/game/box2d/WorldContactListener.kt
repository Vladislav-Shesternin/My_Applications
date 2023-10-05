package com.lohina.titantreasuretrove.game.box2d

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.lohina.titantreasuretrove.util.log

object WorldContactListener: ContactListener {

    private var timeBeginContact = System.currentTimeMillis()
    private var timeAY = 0L

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as AbstractBody)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as AbstractBody)

    override fun beginContact(contact: Contact) {
        with(contact) {
            playSound(abstractBodyA, abstractBodyB)
            abstractBodyA.beginContact(abstractBodyB)
            abstractBodyA.beginContact(abstractBodyB)
        }
    }

    override fun endContact(contact: Contact) {
        with(contact) {
            abstractBodyA.endContact(abstractBodyA)
            abstractBodyB.endContact(abstractBodyB)
        }
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {}

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {}

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun playSound(bodyA: AbstractBody, bodyB: AbstractBody) {
        val soundUtil = bodyA.screenBox2d.game.soundUtil
        val arrAB     = arrayOf(bodyA, bodyB)

        if (arrAB.all { it.fixtureDef.isSensor.not() }) {
            when {
                arrAB.any { it.bodyDef.type == BodyDef.BodyType.StaticBody }
                -> if (System.currentTimeMillis().minus(timeAY) >= 2000) {
                    soundUtil.apply { play(AY) }
                    timeAY = System.currentTimeMillis()
                }
                arrAB.any { it.bodyDef.type == BodyDef.BodyType.DynamicBody }
                -> if (System.currentTimeMillis().minus(timeBeginContact) >= 500) {
                    soundUtil.apply { play(ITEM) }
                    timeBeginContact = System.currentTimeMillis()
                }
            }
        }
    }

}