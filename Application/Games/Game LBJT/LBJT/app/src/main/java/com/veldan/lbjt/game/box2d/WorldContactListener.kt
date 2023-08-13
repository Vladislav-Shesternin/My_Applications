package com.veldan.lbjt.game.box2d

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

object WorldContactListener: ContactListener {

    private var timeContactBorders = 0L
    private var timeContactStatic  = 0L
    private var timeContactDynamic = 0L

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
                arrAB.any { it.id == BodyId.BORDERS }
                -> if (timeMinus(timeContactBorders) >= 210) {
                    soundUtil.apply { play(BORDER) }
                    timeContactBorders = System.currentTimeMillis()
                }

                arrAB.any { it.bodyDef.type == BodyDef.BodyType.StaticBody }
                -> if (timeMinus(timeContactStatic) >= 200) {
                    soundUtil.apply { play(METAL) }
                    timeContactStatic = System.currentTimeMillis()
                }

                arrAB.any { it.bodyDef.type == BodyDef.BodyType.DynamicBody }
                -> if (timeMinus(timeContactDynamic) >= 150) {
                    soundUtil.apply { play(CLUNK) }
                    timeContactDynamic = System.currentTimeMillis()
                }
            }
        }
    }

    private fun timeMinus(time: Long) = System.currentTimeMillis().minus(time)

}