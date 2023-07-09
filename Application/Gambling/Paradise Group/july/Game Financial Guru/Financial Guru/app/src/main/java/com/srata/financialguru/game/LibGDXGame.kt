package com.srata.financialguru.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.srata.financialguru.MainActivity
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.screens.SaploshScreen
import com.srata.financialguru.game.utils.GameColor
import com.srata.financialguru.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var colorSKY = GameColor.sky

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SaploshScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorSKY)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}