package com.bezdrodovam.cryptoinsightspro.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.bezdrodovam.cryptoinsightspro.MainActivity
import com.bezdrodovam.cryptoinsightspro.game.manager.NavigationManager
import com.bezdrodovam.cryptoinsightspro.game.screens.SaploshScreen
import com.bezdrodovam.cryptoinsightspro.game.utils.GameColor
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var colorPupka = GameColor.puplik

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SaploshScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorPupka)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}