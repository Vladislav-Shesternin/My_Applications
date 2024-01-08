package aviator.original.win.game.box2d.bodies

import aviator.original.win.game.actors.image.AImage
import aviator.original.win.game.box2d.AbstractBody
import aviator.original.win.game.box2d.BodyId
import aviator.original.win.game.screens.OriginalShopScreen
import aviator.original.win.game.utils.advanced.AdvancedBox2dScreen
import aviator.original.win.game.utils.advanced.AdvancedGroup
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef

class BAvia(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = "circle"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())

    override var id            = BodyId.Game.AVIA
    override val collisionList = mutableListOf(BodyId.Game.COIN, BodyId.Game.ENEMY)

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getRegionByType() = when (val s = OriginalShopScreen.AVIA) {
        OriginalShopScreen.AviaType._800  -> screenBox2d.game.gameAssets.aviaList[s.avia_index]
        OriginalShopScreen.AviaType._1500 -> screenBox2d.game.gameAssets.aviaList[s.avia_index]
        OriginalShopScreen.AviaType._2000 -> screenBox2d.game.gameAssets.aviaList[s.avia_index]
        OriginalShopScreen.AviaType._3500 -> screenBox2d.game.gameAssets.aviaList[s.avia_index]
    }

}