package com.zeuse.zeusjackpotjamboree.game.box2d

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.zeuse.zeusjackpotjamboree.util.log

object WorldContactListener: ContactListener {

    private var timeBeginContact = System.currentTimeMillis()

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

        if (
            arrAB.all { it.fixtureDef.isSensor.not() } &&
            System.currentTimeMillis().minus(timeBeginContact) >= 150
        ) {

            when {
                arrAB.any { it.id == BodyId.BORDERS }                         -> soundUtil.apply { play(BORDER, false) }
                arrAB.any { it.bodyDef.type == BodyDef.BodyType.DynamicBody } -> if (System.currentTimeMillis().minus(timeBeginContact) >= 200) soundUtil.apply { play(ITEM, false) }
            }

            timeBeginContact = System.currentTimeMillis()

        }
    }

}