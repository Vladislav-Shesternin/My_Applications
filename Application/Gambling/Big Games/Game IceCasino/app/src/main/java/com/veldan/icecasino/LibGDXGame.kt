package com.veldan.icecasino

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.icecasino.assets.FontManager
import com.veldan.icecasino.assets.SpriteManager
import com.veldan.icecasino.screens.GameScreen
import com.veldan.icecasino.screens.MenuScreen
import com.veldan.icecasino.utils.NavigationUtil
import com.veldan.icecasino.utils.Once

lateinit var game: LibGDXGame private set
lateinit var assetManager: AssetManager

class LibGDXGame : Game() {

    private val color = Color(11f / 255, 79f / 255, 141f / 255, 1f)
    private val onceLoadAssets = Once()



    override fun create() {
        game = this
        assetManager = AssetManager()
        loadAssets()
    }

    override fun render() {
        ScreenUtils.clear(color)

        if (assetManager.update()) {
            onceLoadAssets.once {
                initAssets()
                NavigationUtil.navigate(MenuScreen())
            }
            super.render()
        }
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }



    private fun loadAssets() {
        SpriteManager.loadAll(assetManager)
        FontManager.load(assetManager, FontManager.FontEnum.FONT)
    }

    private fun initAssets() {
        SpriteManager.initAll(assetManager)
        FontManager.init(assetManager, FontManager.FontEnum.FONT)
    }

}