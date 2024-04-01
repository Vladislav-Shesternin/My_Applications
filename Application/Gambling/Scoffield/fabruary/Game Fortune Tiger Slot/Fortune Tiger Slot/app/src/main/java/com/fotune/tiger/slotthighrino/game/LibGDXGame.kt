package com.fotune.tiger.slotthighrino.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.fotune.tiger.slotthighrino.MainActivity
import com.fotune.tiger.slotthighrino.game.manager.MusicManager
import com.fotune.tiger.slotthighrino.game.manager.NavigationManager
import com.fotune.tiger.slotthighrino.game.manager.SoundManager
import com.fotune.tiger.slotthighrino.game.manager.SpriteManager
import com.fotune.tiger.slotthighrino.game.manager.util.MusicUtil
import com.fotune.tiger.slotthighrino.game.manager.util.SoundUtil
import com.fotune.tiger.slotthighrino.game.manager.util.SpriteUtil
import com.fotune.tiger.slotthighrino.game.screens.LoaderScreen
import com.fotune.tiger.slotthighrino.game.utils.GameColor
import com.fotune.tiger.slotthighrino.game.utils.advanced.AdvancedGame
import com.fotune.tiger.slotthighrino.game.utils.disposeAll
import com.fotune.tiger.slotthighrino.util.cancelCoroutinesAll
import com.fotune.tiger.slotthighrino.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }
    val allAssets    by lazy { SpriteUtil.AllAssets() }

    val coroutine = CoroutineScope(Dispatchers.Default)

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

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
            disposeAll(musicUtil, assetManager)
            cancelCoroutinesAll(coroutine)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}