package aer.com.gamesas.mobile.slot.game

import aer.com.gamesas.mobile.slot.MainActivity
import aer.com.gamesas.mobile.slot.game.manager.NavigationManager
import aer.com.gamesas.mobile.slot.game.screens.SplashScreen
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedGame
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils

lateinit var game     : LibGDXGame private set
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set


    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.CLEAR)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}