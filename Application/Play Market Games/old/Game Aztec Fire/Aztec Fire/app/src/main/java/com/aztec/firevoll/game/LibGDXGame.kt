package com.aztec.firevoll.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.aztec.firevoll.MainActivity
import com.aztec.firevoll.game.manager.NavigationManager
import com.aztec.firevoll.game.screens.SapolskiyScreen
import com.aztec.firevoll.game.util.GameColor
import com.aztec.firevoll.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SapolskiyScreen())
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