package com.pharhaslo.slo7.game.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.pharhaslo.slo7.game.assets.util.FontBPMData

object FontBMPManager {

    var loadListFont = mutableListOf<IEnumFont>()



    fun load(assetManager: AssetManager) {
        with(assetManager) { loadListFont.onEach {
                load(it.data.path, BitmapFont::class.java, BitmapFontLoader.BitmapFontParameter().apply {
                    minFilter = Texture.TextureFilter.Linear
                    magFilter = Texture.TextureFilter.Linear
                })
        } }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.data.font = assetManager[it.data.path, BitmapFont::class.java] }
    }



    enum class EnumFont(override val data: FontBPMData): IEnumFont {
        PROGRESS(FontBPMData("font/BMP/progress/font.fnt"))
    }



    interface IEnumFont {
        val data: FontBPMData
    }
}