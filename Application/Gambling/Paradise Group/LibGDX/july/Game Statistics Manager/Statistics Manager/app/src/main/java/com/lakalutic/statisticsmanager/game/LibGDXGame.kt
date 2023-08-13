package com.lakalutic.statisticsmanager.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.lakalutic.statisticsmanager.LambarginiActivity
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.screens.MarrionetkaScreen
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var whitik = Color.WHITE

class LibGDXGame(val activity: LambarginiActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(MarrionetkaScreen())
    }

    override fun render() {
        ScreenUtils.clear(whitik)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}