package com.veldan.bigwinslots777.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.veldan.bigwinslots777.manager.assets.util.FontBPMData
import com.veldan.bigwinslots777.manager.assets.util.FontTTFData

object FontBMPManager {

    private const val pathGold200 = "font/BMP/gold_200/font.fnt"
    private const val pathGold70  = "font/BMP/gold_70/font.fnt"

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
        val font_200 = FontBPMData(pathGold200)
        val font_70  = FontBPMData(pathGold70)

        val values get() = listOf<FontBPMData>(font_200, font_70)
    }



    interface IFont {

        val values get() = listOf<FontBPMData>()
    }
}