package qbl.bisriymyach.QuickBall.pitopilot

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.badlogic.gdx.utils.Array
import qbl.bisriymyach.QuickBall.tidams.mim
import qbl.bisriymyach.QuickBall.hotvils.Bibash
import qbl.bisriymyach.QuickBall.fastergan.currentTimeMinus
import qbl.bisriymyach.QuickBall.tidams.log

class aga_ugu : ContactListener {

    private var timeContactStatic = 0L

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as Bibash)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as Bibash)

    private val tmpArray = Array<Bibash>().apply { setSize(2) }
    private lateinit var soundUtil: mim

    override fun beginContact(contact: Contact) {
        with(contact) {
            playSound(abstractBodyA, abstractBodyB)
            abstractBodyA.beginContact(abstractBodyB)
            abstractBodyB.beginContact(abstractBodyA)
        }
    }

    val d = ContactBlock { bodyA, bodyB ->
        timeContactStatic += 1L
    }

    override fun endContact(contact: Contact) {
        with(contact) {
            abstractBodyA.endContact(abstractBodyB)
            abstractBodyB.endContact(abstractBodyA)
            d.block(abstractBodyA, abstractBodyB)
        }
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {}

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {}

    private fun playSound(bodyA: Bibash, bodyB: Bibash) {
        tmpArray[0] = bodyA
        tmpArray[1] = bodyB

        soundUtil = bodyA.franke.game.soundUtil

        if (tmpArray.all { it.kardinallo.isSensor.not() }) {
            when {
                tmpArray.any { it.ronaldo.type == BodyDef.BodyType.StaticBody }
                -> if (currentTimeMinus(timeContactStatic) >= 100) {
                    soundUtil.apply {
                        play(bum, 50f)
                    }
                    timeContactStatic = System.currentTimeMillis()
                }
            }
        }

    }

    fun interface ContactBlock {
        fun block(bodyA: Bibash, bodyB: Bibash)
    }

}