package com.tutotoons.app.kpopsiescuteunicornpet.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.tutotoons.app.kpopsiescuteunicornpet.MainActivity
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.util.MusicUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.util.ParticleEffectUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.util.SoundUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.util.SpriteUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.screens.LoaderScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.GameColor
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.dataStore.IsTutorialsUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.dataStore.LevelUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.dataStore.RecordUtil
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.disposeAll
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class GDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set

    val assetsAll    by lazy { SpriteUtil.All() }
    val assetsLoader by lazy { SpriteUtil.Loader() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

    val particleEffectUtil by lazy { ParticleEffectUtil() }

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    val coroutine = CoroutineScope(Dispatchers.Default)

    val levelUtil       = LevelUtil(coroutine)
    val recordUtil      = RecordUtil(coroutine, levelUtil)
    val isTutorialsUtil = IsTutorialsUtil(coroutine)

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        particleEffectManager = ParticleEffectManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            coroutine.cancel()
            disposableSet.disposeAll()
            disposeAll(assetManager, musicUtil)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}