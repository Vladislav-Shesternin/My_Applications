package com.zaykoa.investmentanalyzer.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.zaykoa.investmentanalyzer.MainActivity
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.screens.SplashScreen
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var lacalut = Color.WHITE
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(lacalut)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}