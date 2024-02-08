package crapto.quantem.ao.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import crapto.quantem.ao.MainActivity
import crapto.quantem.ao.game.manager.NavigationManager
import crapto.quantem.ao.game.screens.SplashScreen
import crapto.quantem.ao.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var colorsha = Color.CLEAR
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorsha)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}