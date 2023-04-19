package com.pharhaslo.slo7.game.utils.language

import com.pharhaslo.slo7.game.assets.SpriteManager

object SpriteUS : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuUSSprite.PLAY.data.texture
    override val OPTIONS get() = SpriteManager.MenuUSSprite.OPTIONS.data.texture
    override val EXIT    get() = SpriteManager.MenuUSSprite.EXIT.data.texture

    override val GO      get() = SpriteManager.GameUSSprite.GO.data.texture
    override val GO_DIS  get() = SpriteManager.GameUSSprite.GO_DIS.data.texture
    override val BET     get() = SpriteManager.GameUSSprite.BET.data.texture
    override val FREE_GO get() = SpriteManager.GameUSSprite.FREE_GO.data.texture
    override val TAKE    get() = SpriteManager.GameUSSprite.TAKE.data.texture
}