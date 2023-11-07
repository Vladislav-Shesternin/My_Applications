package novibet.leoforos.irakloiu.office.helper.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import novibet.leoforos.irakloiu.office.helper.game.manager.NavigationManager
import novibet.leoforos.irakloiu.office.helper.game.manager.SpriteManager
import novibet.leoforos.irakloiu.office.helper.game.manager.util.SpriteUtil
import novibet.leoforos.irakloiu.office.helper.game.screens.LoaderScreen
import novibet.leoforos.irakloiu.office.helper.MainActivity
import novibet.leoforos.irakloiu.office.helper.game.utils.GameColor
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedGame
import novibet.leoforos.irakloiu.office.helper.game.utils.disposeAll
import novibet.leoforos.irakloiu.office.helper.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    companion object {
        val PASSWORD = "qwerty123456789PaRoL"
    }

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set

    val spriteUtil       by lazy { SpriteUtil.CommonAssets() }
    val spriteUtilSplash by lazy { SpriteUtil.SplashAssets() }

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}