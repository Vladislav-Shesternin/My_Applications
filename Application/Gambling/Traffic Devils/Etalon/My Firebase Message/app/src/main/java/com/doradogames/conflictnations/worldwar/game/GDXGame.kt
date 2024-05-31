package com.doradogames.conflictnations.worldwar.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.doradogames.conflictnations.worldwar.GameActivity
import com.doradogames.conflictnations.worldwar.game.manager.*
import com.doradogames.conflictnations.worldwar.game.manager.util.MusicUtil
import com.doradogames.conflictnations.worldwar.game.manager.util.ParticleEffectUtil
import com.doradogames.conflictnations.worldwar.game.manager.util.SoundUtil
import com.doradogames.conflictnations.worldwar.game.manager.util.SpriteUtil
import com.doradogames.conflictnations.worldwar.game.screens.LoaderScreen
import com.doradogames.conflictnations.worldwar.game.utils.GameColor
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedGame
import com.doradogames.conflictnations.worldwar.game.utils.disposeAll
import com.doradogames.conflictnations.worldwar.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class GDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set

    val assetsLoader by lazy { SpriteUtil.Loader() }
    val assetsAll    by lazy { SpriteUtil.All() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

    val particleEffectUtil by lazy { ParticleEffectUtil() }

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    val coroutine = CoroutineScope(Dispatchers.Default)

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