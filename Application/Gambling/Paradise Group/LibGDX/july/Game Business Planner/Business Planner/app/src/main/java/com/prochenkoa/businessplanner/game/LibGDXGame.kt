package com.prochenkoa.businessplanner.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.prochenkoa.businessplanner.MercedesActivity
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.screens.BiznesScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var whitik = Color.WHITE

class LibGDXGame(val activity: MercedesActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(BiznesScreen())
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