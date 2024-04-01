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

class aga_ugu: ContactListener {

    private var timeContactBorders = 0L
    private var timeContactStatic  = 0L
    private var timeContactDynamic = 0L

    private val Contact.abstractBodyA get() = (fixtureA.body.userData as Bibash)
    private val Contact.abstractBodyB get() = (fixtureB.body.userData as Bibash)

    private val tmpArray = Array<Bibash>().apply { setSize(2) }
    private lateinit var soundUtil: mim

    var beginContactBlockArray = Array<ContactBlock>()
    var endContactBlockArray   = Array<ContactBlock>()

    override fun beginContact(contact: Contact) {
        with(contact) {
            playSound(abstractBodyA, abstractBodyB)
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

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun playSound(bodyA: Bibash, bodyB: Bibash) {
        tmpArray[0] = bodyA
        tmpArray[1] = bodyB

        soundUtil = bodyA.franke.game.soundUtil

        if (tmpArray.all { it.kardinallo.isSensor.not() }) {
            when {
//                tmpArray.any { it.id == BodyId.BORDERS }
//                -> if (currentTimeMinus(timeContactBorders) >= 210) {
//                    soundUtil.apply { play(udars.random()) }
//                    timeContactBorders = System.currentTimeMillis()
//                }

                tmpArray.any { it.ronaldo.type == BodyDef.BodyType.StaticBody }
                -> if (currentTimeMinus(timeContactStatic) >= 100) {
                    soundUtil.apply {
                        play(bum, 50f)
                    }
                    timeContactStatic = System.currentTimeMillis()
                }

//                tmpArray.any { it.bodyDef.type == BodyDef.BodyType.DynamicBody }
//                -> if (currentTimeMinus(timeContactDynamic) >= 150) {
//                    soundUtil.apply { play(dot.random(), volumeLevel * 0.2f) }
//                    timeContactDynamic = System.currentTimeMillis()
//                }
            }
        }

    }

    // ---------------------------------------------------
    // SAM
    // ---------------------------------------------------

    fun interface ContactBlock { fun block(bodyA: Bibash, bodyB: Bibash) }

}