package com.tmesfo.frtunes.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.tmesfo.frtunes.game.manager.assets.util.FontBPMData

object FontBMPManager {

   // private const val pathGold200 = "font/BMP/gold_200/font.fnt"

    var loadListFont = mutableListOf<FontBPMData>()



    fun load(assetManager: AssetManager) {
        with(assetManager) { loadListFont.onEach {
                load(it.path, BitmapFont::class.java, BitmapFontLoader.BitmapFontParameter().apply {
                    minFilter = Texture.TextureFilter.Linear
                    magFilter = Texture.TextureFilter.Linear
                })
        } }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.font = assetManager[it.path, BitmapFont::class.java] }
    }



    object GoldFont {
       // override val font_200 = FontBPMData(pathGold200)
    }



    interface IFont {
       // val font_200: FontBPMData

        val values get() = listOf<FontBPMData>(/*font_200*/)
    }
}