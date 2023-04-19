package com.veldan.junglego.utils.language

import com.veldan.junglego.assets.SpriteManager

object SpriteUS : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuUSSprite.PLAY.textureData.texture
    override val OPTIONS get() = SpriteManager.MenuUSSprite.OPTIONS.textureData.texture
    override val EXIT    get() = SpriteManager.MenuUSSprite.EXIT.textureData.texture

    override val SPIN    get() = SpriteManager.GameUSSprite.SPIN.textureData.texture
    override val WAIT    get() = SpriteManager.GameUSSprite.WAIT.textureData.texture
    override val BET     get() = SpriteManager.GameUSSprite.BET.textureData.texture
}