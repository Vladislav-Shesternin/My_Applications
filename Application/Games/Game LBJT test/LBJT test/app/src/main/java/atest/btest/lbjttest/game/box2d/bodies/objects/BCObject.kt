package atest.btest.lbjttest.game.box2d.bodies.objects

import atest.btest.lbjttest.game.actors.image.AImage
import atest.btest.lbjttest.game.box2d.AbstractBody
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedGroup
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class BCObject(
    override val screenBox2d: AdvancedBox2dScreen,
    private val bodyType: BodyType,
): AbstractBody() {

    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = bodyType
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.3f
        friction    = 0.3f
    }
    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())

    fun getActor(): AImage? = actor as? AImage

    private fun getRegionByType(): TextureRegion = when (bodyType) {
        BodyType.StaticBody    -> screenBox2d.game.assets.C_STATIC
        BodyType.KinematicBody -> screenBox2d.game.assets.C_KINEMATIC
        BodyType.DynamicBody   -> screenBox2d.game.assets.C_DYNAMIC
    }

    fun update(region: TextureRegion) {
        getActor()?.drawable = TextureRegionDrawable(region)
    }

}