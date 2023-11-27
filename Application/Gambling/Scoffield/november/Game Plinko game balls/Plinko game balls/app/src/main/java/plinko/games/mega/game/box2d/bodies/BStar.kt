package plinko.games.mega.game.box2d.bodies

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import plinko.games.mega.game.actors.image.AImage
import plinko.games.mega.game.box2d.AbstractBody
import plinko.games.mega.game.box2d.BodyId
import plinko.games.mega.game.screens.levelBallIndex
import plinko.games.mega.game.utils.advanced.AdvancedBox2dScreen
import plinko.games.mega.game.utils.advanced.AdvancedGroup

class BStar(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        isSensor=true
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, screenBox2d.game.gameAssets.STAR).apply {
        val t = (3..6).random()/10f
        val d = (40..60).random().toFloat()
        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, d, t, Interpolation.sine),
            Actions.moveBy(0f, -d, t, Interpolation.sine),
        )))
    }
}