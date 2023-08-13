package com.leto.advancedcalculator.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.leto.advancedcalculator.NewVebkaZaebcaActivity
import com.leto.advancedcalculator.game.manager.NavigationManager
import com.leto.advancedcalculator.game.screens.SatisfactionScreen
import com.leto.advancedcalculator.game.utils.GameColor
import com.leto.advancedcalculator.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var tri_color = Color.WHITE

class LibGDXGame(val activity: NewVebkaZaebcaActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SatisfactionScreen())
    }

    override fun render() {
        ScreenUtils.clear(tri_color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}