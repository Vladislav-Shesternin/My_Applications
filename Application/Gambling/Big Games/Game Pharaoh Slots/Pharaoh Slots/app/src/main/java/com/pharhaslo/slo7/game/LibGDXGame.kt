package com.pharhaslo.slo7.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.pharhaslo.slo7.game.advanced.AdvancedGame
import com.pharhaslo.slo7.game.assets.util.MusicUtil
import com.pharhaslo.slo7.game.manager.NavigationManager
import com.pharhaslo.slo7.game.screens.SplashScreen
import com.pharhaslo.slo7.game.utils.language.LanguageSprite

lateinit var game: LibGDXGame private set
lateinit var assetManager: AssetManager private set
lateinit var languageSprite: LanguageSprite

class LibGDXGame : AdvancedGame() {

    private val color = Color.BLACK



    override fun create() {
        game = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        MusicUtil.dispose()
    }

}