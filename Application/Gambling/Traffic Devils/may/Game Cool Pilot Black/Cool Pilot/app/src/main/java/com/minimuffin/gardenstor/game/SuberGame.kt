package com.minimuffin.gardenstor.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.minimuffin.gardenstor.game.manager.MusicManager
import com.minimuffin.gardenstor.game.manager.NavigationManager
import com.minimuffin.gardenstor.game.manager.SoundManager
import com.minimuffin.gardenstor.game.manager.SpriteManager
import com.minimuffin.gardenstor.game.manager.util.MusicUtil
import com.minimuffin.gardenstor.game.manager.util.SoundUtil
import com.minimuffin.gardenstor.game.manager.util.SpriteUtil
import com.minimuffin.gardenstor.game.screens.ZagruzonScreen
import com.minimuffin.gardenstor.game.utils.SuberColor
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedGame
import com.minimuffin.gardenstor.game.utils.disposeAll
import com.minimuffin.gardenstor.util.log

class SuberGame(val activity: com.minimuffin.gardenstor.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val assets by lazy { SpriteUtil.VseAssets() }
    val fisters by lazy { SpriteUtil.Start() }

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }

    var backgroundColor = SuberColor.gego
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        activity.lottie.show()

        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(ZagruzonScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(assetManager, musicUtil)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}