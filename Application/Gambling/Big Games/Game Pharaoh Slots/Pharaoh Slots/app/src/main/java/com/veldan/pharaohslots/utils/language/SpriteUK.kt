package com.veldan.pharaohslots.utils.language

import com.veldan.pharaohslots.assets.SpriteManager

object SpriteUK : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuUKSprite.PLAY.data.texture
    override val OPTIONS get() = SpriteManager.MenuUKSprite.OPTIONS.data.texture
    override val EXIT    get() = SpriteManager.MenuUKSprite.EXIT.data.texture

    override val GO      get() = SpriteManager.GameUKSprite.GO.data.texture
    override val GO_DIS  get() = SpriteManager.GameUKSprite.GO_DIS.data.texture
    override val BET     get() = SpriteManager.GameUKSprite.BET.data.texture
    override val FREE_GO get() = SpriteManager.GameUKSprite.FREE_GO.data.texture
    override val TAKE    get() = SpriteManager.GameUKSprite.TAKE.data.texture
}