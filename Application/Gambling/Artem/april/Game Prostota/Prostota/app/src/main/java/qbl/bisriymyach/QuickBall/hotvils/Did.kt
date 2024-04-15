package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import qbl.bisriymyach.QuickBall.Ebufren

class Did(override val franke: Ebufren) : Bibash() {
    override val bobo = "circle"

    override val ronaldo = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val kardinallo = FixtureDef().apply {
        isSensor = true
    }

}