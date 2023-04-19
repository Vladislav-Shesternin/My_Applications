package com.veldan.junglego.manager

import com.veldan.junglego.utils.language.LanguageSprite
import com.veldan.junglego.utils.language.SpriteRU
import com.veldan.junglego.utils.language.SpriteUK
import com.veldan.junglego.utils.language.SpriteUS
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