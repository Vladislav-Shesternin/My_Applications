package com.tigerfortune.jogo.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.tigerfortune.jogo.game.actors.image.AImage
import com.tigerfortune.jogo.game.box2d.AbstractBody
import com.tigerfortune.jogo.game.box2d.BodyId
import com.tigerfortune.jogo.game.screens.TigerChooseScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedBox2dScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup

class BTiger(override val screenBox2d: AdvancedBox2dScreen): AbstractBody() {
    override val name       = getNameByType()
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        friction    = 0.5f
    }

    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())

    override var id = BodyId.Game.TIGER

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getNameByType() = when (TigerChooseScreen.TIGER_TYPE) {
        TigerChooseScreen.TigerType.TIGER_1 -> "tiger1"
        TigerChooseScreen.TigerType.TIGER_2 -> "tiger2"
    }

    private fun getRegionByType() = when (TigerChooseScreen.TIGER_TYPE) {
        TigerChooseScreen.TigerType.TIGER_1 -> screenBox2d.game.gameAssets.tiger_1
        TigerChooseScreen.TigerType.TIGER_2 -> screenBox2d.game.gameAssets.tiger_2
    }

}