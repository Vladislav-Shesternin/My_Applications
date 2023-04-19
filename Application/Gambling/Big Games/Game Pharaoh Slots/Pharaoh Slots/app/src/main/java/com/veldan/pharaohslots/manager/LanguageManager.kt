package com.veldan.pharaohslots.manager

import com.veldan.pharaohslots.utils.language.LanguageSprite
import com.veldan.pharaohslots.utils.language.SpriteRU
import com.veldan.pharaohslots.utils.language.SpriteUK
import com.veldan.pharaohslots.utils.language.SpriteUS
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