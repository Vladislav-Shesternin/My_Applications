package jogos.tigerfortune.tighrino.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import jogos.tigerfortune.tighrino.game.actors.image.AImage
import jogos.tigerfortune.tighrino.game.box2d.AbstractBody
import jogos.tigerfortune.tighrino.game.box2d.BodyId
import jogos.tigerfortune.tighrino.game.screens.TChooseScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedBox2dScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedGroup

class BTiger(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = getNameByType()
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 2f
        restitution = 0.4f
        friction    = 0.2f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())

    override var id = BodyId.Game.TIGER

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getNameByType() = when (TChooseScreen.TIGER_TYPE) {
        TChooseScreen.TigerType.TIGER_1 -> "tiger1"
        TChooseScreen.TigerType.TIGER_2 -> "tiger2"
    }

    private fun getRegionByType() = when (TChooseScreen.TIGER_TYPE) {
        TChooseScreen.TigerType.TIGER_1 -> screenBox2d.game.gameAssets.TIGER1
        TChooseScreen.TigerType.TIGER_2 -> screenBox2d.game.gameAssets.TIGER2
    }

}