package com.metanit.metrixmanager.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.metanit.metrixmanager.RenoActivity
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.screens.SapranoScreen
import com.metanit.metrixmanager.game.utils.GameColor
import com.metanit.metrixmanager.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var oranged = Color.valueOf("F8AC49")

class LibGDXGame(val activity: RenoActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()
        NavigationManager.navigate(SapranoScreen())
    }

    override fun render() {
        ScreenUtils.clear(oranged)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}