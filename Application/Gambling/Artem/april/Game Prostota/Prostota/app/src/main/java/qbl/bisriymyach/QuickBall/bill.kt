package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import qbl.bisriymyach.QuickBall.hotvils.Bibash
import qbl.bisriymyach.QuickBall.hotvils.Sitriska
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi
import java.util.concurrent.atomic.AtomicBoolean

class bill(override val franke: Ebufren): Bibash() {
    override val bobo       = "circle"
    override val ronaldo    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val kardinallo = FixtureDef().apply {
        density     = 20f
        restitution = 0.1f
        friction    = 0.1f
    }

    override var ultima: Oi_oi_uoi? = malto(franke, if (Sitriska.boll == Sitriska.Baraboli.LEft) franke.game.allAssets.bl_pink else franke.game.allAssets.bl_yellow)

    var isWork = AtomicBoolean(true)



}