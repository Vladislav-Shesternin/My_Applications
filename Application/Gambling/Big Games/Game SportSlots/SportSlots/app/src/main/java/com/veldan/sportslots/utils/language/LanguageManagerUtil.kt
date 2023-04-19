package com.veldan.sportslots.utils.language

import com.veldan.sportslots.utils.language.LanguageManagerUtil.Language.*
import java.util.*

object LanguageManagerUtil {

    val languageSprite: LanguageSprite
        get() = when (Locale.getDefault().language) {
            UK.lang -> SpriteUK
            RU.lang -> SpriteRU
            US.lang -> SpriteUS
            else    -> SpriteUS
        }



    enum class Language(val lang: String) {
        UK("uk"),
        RU("ru"),
        US("us"),
    }


}