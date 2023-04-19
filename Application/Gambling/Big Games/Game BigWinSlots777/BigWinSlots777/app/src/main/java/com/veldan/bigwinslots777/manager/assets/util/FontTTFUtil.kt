package com.veldan.bigwinslots777.manager.assets.util

import com.veldan.bigwinslots777.manager.assets.FontTTFManager
import com.veldan.bigwinslots777.utils.language.Language

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.AmaranteFont
        else -> FontTTFManager.NotoSansFont
    }

}