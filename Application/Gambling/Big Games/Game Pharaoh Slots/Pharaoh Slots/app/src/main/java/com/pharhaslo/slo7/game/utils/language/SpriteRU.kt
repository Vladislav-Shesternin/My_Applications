package com.pharhaslo.slo7.game.utils.language

import com.pharhaslo.slo7.game.assets.SpriteManager

object SpriteRU : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuRUSprite.PLAY.data.texture
    override val OPTIONS get() = SpriteManager.MenuRUSprite.OPTIONS.data.texture
    override val EXIT    get() = SpriteManager.MenuRUSprite.EXIT.data.texture

    override val GO      get() = SpriteManager.GameRUSprite.GO.data.texture
    override val GO_DIS  get() = SpriteManager.GameRUSprite.GO_DIS.data.texture
    override val BET     get() = SpriteManager.GameRUSprite.BET.data.texture
    override val FREE_GO get() = SpriteManager.GameRUSprite.FREE_GO.data.texture
    override val TAKE    get() = SpriteManager.GameRUSprite.TAKE.data.texture
}