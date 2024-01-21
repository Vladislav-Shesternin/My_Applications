package jogo.fortunetiger.tighrino.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import jogo.fortunetiger.tighrino.game.actors.image.AImage
import jogo.fortunetiger.tighrino.game.box2d.AbstractBody
import jogo.fortunetiger.tighrino.game.box2d.BodyId
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedBox2dScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedGroup
import java.util.concurrent.atomic.AtomicBoolean

class BItem(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    private val assets = screenBox2d.game.gameAssets

    override var actor: AdvancedGroup? = AImage(screenBox2d)

    // Field
    val atomicBoolean = AtomicBoolean(true)

    private var index = 0

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateImage() {
        index = (0..4).random()
        (actor as AImage).drawable = TextureRegionDrawable(assets.items[index])
    }

    fun updateId() {
        id = BodyId.Game.items[index]
    }

}