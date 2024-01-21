package jogo.fortunetiger.tighrino.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import jogo.fortunetiger.tighrino.game.actors.image.AImage
import jogo.fortunetiger.tighrino.game.box2d.AbstractBody
import jogo.fortunetiger.tighrino.game.box2d.BodyId
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedBox2dScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItemBomb(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 3f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.bim)

    override var id            = BodyId.Game.BOMB
    override val collisionList = mutableListOf(BodyId.Game.TIGER)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}