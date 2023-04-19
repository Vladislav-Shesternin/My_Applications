package com.veldan.junglego.utils.language

import com.veldan.junglego.assets.SpriteManager

object SpriteUK : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuUKSprite.PLAY.textureData.texture
    override val OPTIONS get() = SpriteManager.MenuUKSprite.OPTIONS.textureData.texture
    override val EXIT    get() = SpriteManager.MenuUKSprite.EXIT.textureData.texture

    override val SPIN    get() = SpriteManager.GameUKSprite.SPIN.textureData.texture
    override val WAIT    get() = SpriteManager.GameUKSprite.WAIT.textureData.texture
    override val BET     get() = SpriteManager.GameUKSprite.BET.textureData.texture
}