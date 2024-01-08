package aviator.original.win.game.box2d.bodies

import aviator.original.win.game.actors.image.AImage
import aviator.original.win.game.box2d.AbstractBody
import aviator.original.win.game.box2d.BodyId
import aviator.original.win.game.utils.advanced.AdvancedBox2dScreen
import aviator.original.win.game.utils.advanced.AdvancedGroup
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import java.util.concurrent.atomic.AtomicBoolean

class BEnemy(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.enemyList.random())

    override var id            = BodyId.Game.ENEMY
    override val collisionList = mutableListOf(BodyId.Game.AVIA)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}