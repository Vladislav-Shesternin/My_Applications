package fball.fteam.fquiz.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import fball.fteam.fquiz.MainActivity
import fball.fteam.fquiz.game.manager.AllAssets
import fball.fteam.fquiz.game.manager.NavigationManager
import fball.fteam.fquiz.game.manager.SpriteManager
import fball.fteam.fquiz.game.screens.SplashScreen
import fball.fteam.fquiz.game.utils.advanced.AdvancedGame
import fball.fteam.fquiz.game.utils.disposeAll
import fball.fteam.fquiz.game.utils.language.LanguageUtil
import fball.fteam.fquiz.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val assets       by lazy { AllAssets()    }
    val languageUtil by lazy { LanguageUtil(activity) }

    var backgroundColor = Color.valueOf("D0D1D5")
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(SplashScreen::class.java.name)
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