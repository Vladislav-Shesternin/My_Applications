package com.pharhaslo.slo7.game.manager

import com.pharhaslo.slo7.game.utils.language.LanguageSprite
import com.pharhaslo.slo7.game.utils.language.SpriteRU
import com.pharhaslo.slo7.game.utils.language.SpriteUK
import com.pharhaslo.slo7.game.utils.language.SpriteUS
import java.util.*

object LanguageManager {

    val languageSprite: LanguageSprite
        get() = when (language) {
            "uk" -> SpriteUK
            "ru" -> SpriteRU
            "us" -> SpriteUS
            else -> SpriteUS
        }

    val language get() = Locale.getDefault().language

}