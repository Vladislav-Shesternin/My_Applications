package iconic.duo.hunt.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import iconic.duo.hunt.game.manager.NavigationManager
import iconic.duo.hunt.game.screens.SplashScreen
import iconic.duo.hunt.game.utils.GameColor
import iconic.duo.hunt.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: iconic.duo.hunt.MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()



        NavigationManager.navigate(SplashScreen())
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