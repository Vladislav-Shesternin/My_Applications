package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import qbl.bisriymyach.QuickBall.Ebufren

class Brat(override val franke: Ebufren) : Bibash() {
    override val bobo = "v"
    override val ronaldo = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val kardinallo = FixtureDef()

}