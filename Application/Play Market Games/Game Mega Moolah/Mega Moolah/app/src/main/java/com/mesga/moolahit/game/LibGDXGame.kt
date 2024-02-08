package com.mesga.moolahit.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.mesga.moolahit.MainActivity
import com.mesga.moolahit.game.manager.NavigationManager
import com.mesga.moolahit.game.screens.FirsterScreen
import com.mesga.moolahit.game.util.GameColor
import com.mesga.moolahit.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(FirsterScreen())
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