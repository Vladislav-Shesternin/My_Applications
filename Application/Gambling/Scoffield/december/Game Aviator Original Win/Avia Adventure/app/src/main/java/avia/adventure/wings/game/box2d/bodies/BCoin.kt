package avia.adventure.wings.game.box2d.bodies

import avia.adventure.wings.game.actors.image.AImage
import avia.adventure.wings.game.box2d.AbstractBody
import avia.adventure.wings.game.box2d.BodyId
import avia.adventure.wings.game.utils.advanced.AdvancedBox2dScreen
import avia.adventure.wings.game.utils.advanced.AdvancedGroup
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import java.util.concurrent.atomic.AtomicBoolean

class BCoin(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.coinList.random())

    override var id            = BodyId.Game.COIN
    override val collisionList = mutableListOf(BodyId.Game.AVIA)

    // Field
    val atomicBoolean = AtomicBoolean(true)

}