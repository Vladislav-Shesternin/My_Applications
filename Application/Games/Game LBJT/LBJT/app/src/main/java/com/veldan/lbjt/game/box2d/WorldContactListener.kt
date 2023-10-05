package com.veldan.lbjt.game.box2d

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.badlogic.gdx.utils.Array
import com.veldan.lbjt.game.manager.util.SoundUtil

import com.veldan.lbjt.util.log

object WorldContactListener: ContactListener {

    private var timeContactBorders = 0L
    private var timeContactStatic  = 0L
    private var timeContactDynamic = 0L

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as AbstractBody)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as AbstractBody)

    private val tmpArray = Array<AbstractBody>().apply { setSize(2) }
    private lateinit var soundUtil: SoundUtil

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
        tmpArray[0] = bodyA
        tmpArray[1] = bodyB

        soundUtil = bodyA.screenBox2d.game.soundUtil

        if (tmpArray.all { it.fixtureDef.isSensor.not() }) {
            when {
                tmpArray.any { it.id == BodyId.BORDERS }
                -> if (timeMinus(timeContactBorders) >= 210) {
                    soundUtil.apply { play(BORDER) }
                    timeContactBorders = System.currentTimeMillis()
                }

                tmpArray.any { it.bodyDef.type == BodyDef.BodyType.StaticBody }
                -> if (timeMinus(timeContactStatic) >= 200) {
                    soundUtil.apply { play(METAL) }
                    timeContactStatic = System.currentTimeMillis()
                }

                tmpArray.any { it.bodyDef.type == BodyDef.BodyType.DynamicBody }
                -> if (timeMinus(timeContactDynamic) >= 150) {
                    soundUtil.apply { play(CLUNK) }
                    timeContactDynamic = System.currentTimeMillis()
                }
            }
        }

    }

    private fun timeMinus(time: Long) = System.currentTimeMillis().minus(time)

}