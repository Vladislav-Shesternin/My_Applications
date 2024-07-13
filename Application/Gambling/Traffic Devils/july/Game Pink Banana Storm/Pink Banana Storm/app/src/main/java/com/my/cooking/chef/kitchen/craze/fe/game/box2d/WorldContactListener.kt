package com.my.cooking.chef.kitchen.craze.fe.game.box2d

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class WorldContactListener: ContactListener {

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractBody)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractBody)

    override fun beginContact(contact: Contact) {
        with(contact) {
            abstractBodyA.beginContact(abstractBodyB)
            abstractBodyB.beginContact(abstractBodyA)
        }
    }

    override fun endContact(contact: Contact) {
        with(contact) {
            abstractBodyA.endContact(abstractBodyB)
            abstractBodyB.endContact(abstractBodyA)
        }
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {}

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {}

}