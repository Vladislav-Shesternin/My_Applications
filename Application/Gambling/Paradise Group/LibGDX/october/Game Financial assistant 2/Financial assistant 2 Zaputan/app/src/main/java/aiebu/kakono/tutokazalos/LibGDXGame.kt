package aiebu.kakono.tutokazalos

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import aiebu.kakono.tutokazalos.soloha.NavigationManager
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.SpriteManager
import aiebu.kakono.tutokazalos.soloha.SpriteUtil
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.AdvancedGame
import aiebu.kakono.tutokazalos.soloha.pisoha.disposeAll
import aiebu.kakono.tutokazalos.parmengo.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var assetManagerLocal: AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set

    val spriteUtil    by lazy { SpriteUtil.CommonAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        assetManagerLocal = AssetManager(LocalFileHandleResolver())
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposeAll(assetManager, assetManagerLocal)
            disposableSet.disposeAll()
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}