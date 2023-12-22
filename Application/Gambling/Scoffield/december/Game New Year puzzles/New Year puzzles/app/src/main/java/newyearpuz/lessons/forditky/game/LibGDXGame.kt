package newyearpuz.lessons.forditky.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import newyearpuz.lessons.forditky.MainActivity
import newyearpuz.lessons.forditky.game.manager.MusicManager
import newyearpuz.lessons.forditky.game.manager.NavigationManager
import newyearpuz.lessons.forditky.game.manager.SoundManager
import newyearpuz.lessons.forditky.game.manager.SpriteManager
import newyearpuz.lessons.forditky.game.manager.util.GameAssets
import newyearpuz.lessons.forditky.game.manager.util.MusicUtil
import newyearpuz.lessons.forditky.game.manager.util.SoundUtil
import newyearpuz.lessons.forditky.game.screens.PuzzleSplashScreen
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedGame
import newyearpuz.lessons.forditky.game.utils.disposeAll
import newyearpuz.lessons.forditky.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    companion object {
        var isMusic = true
    }

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val gameAssets   by lazy { GameAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(PuzzleSplashScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(musicUtil, assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}