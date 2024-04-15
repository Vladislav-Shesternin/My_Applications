package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import qbl.bisriymyach.QuickBall.hotvils.Bibash

class harizma(override val franke: Ebufren) : Bibash() {
    override val bobo = "h"
    override val ronaldo = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val kardinallo = FixtureDef()
}