package com.tmesfo.frtunes.game.manager.assets.util

import com.tmesfo.frtunes.game.manager.assets.FontTTFManager
import com.tmesfo.frtunes.game.utils.language.Language

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.AbrilFatfaceFont
        else -> FontTTFManager.NotoSansFont
    }

}