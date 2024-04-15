package qbl.bisriymyach.QuickBall.sudams

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import qbl.bisriymyach.QuickBall.malto
import qbl.bisriymyach.QuickBall.hotvils.Bibash
import qbl.bisriymyach.QuickBall.hotvils.Omred
import qbl.bisriymyach.QuickBall.Ebufren

class Materasu(override val franke: Ebufren) : Bibash() {
    override val bobo = "circle"
    override val ronaldo = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val kardinallo = FixtureDef().apply {
        restitution = (10..50).random() / 100f
    }

    override var ultima: Oi_oi_uoi? = malto(franke, franke.game.allAssets.wita)

    override var id: String = Omred.STATIC

    override val collisionList = mutableListOf(Omred.BALL)

}