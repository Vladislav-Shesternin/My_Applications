package com.hellhot.competition.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.hellhot.competition.MainActivity
import com.hellhot.competition.game.manager.NavigationManager
import com.hellhot.competition.game.screens.LoadingsScreen
import com.hellhot.competition.game.util.GameColor
import com.hellhot.competition.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(LoadingsScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.background)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}