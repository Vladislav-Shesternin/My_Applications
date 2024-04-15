package qbl.bisriymyach.QuickBall.pitopilot

import com.badlogic.gdx.physics.box2d.ContactFilter
import com.badlogic.gdx.physics.box2d.Fixture
import qbl.bisriymyach.QuickBall.hotvils.Bibash

class solistkagroupveagra : ContactFilter {

    override fun shouldCollide(fixtureA: Fixture, fixtureB: Fixture): Boolean {
        return (fixtureA.body.userData as Bibash).collisionList.contains((fixtureB.body.userData as Bibash).id) &&
                (fixtureB.body.userData as Bibash).collisionList.contains((fixtureA.body.userData as Bibash).id)
    }
}