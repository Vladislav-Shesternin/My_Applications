package com.centurygames.idlecourie.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.centurygames.idlecourie.game.manager.*
import com.centurygames.idlecourie.game.manager.util.MusicUtil
import com.centurygames.idlecourie.game.manager.util.SoundUtil
import com.centurygames.idlecourie.game.manager.util.SpriteUtil
import com.centurygames.idlecourie.game.screens.Lodinga_HERA_Screen
import com.centurygames.idlecourie.game.utils.HelloMotoColor
import com.centurygames.idlecourie.game.utils.advanced.AdvancedGame
import com.centurygames.idlecourie.game.utils.disposeAll
import com.centurygames.idlecourie.util.log

class HelloMotoGame(val activity: com.centurygames.idlecourie.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val valhalla by lazy { SpriteUtil.Valuha() }
    val gudomidron by lazy { SpriteUtil.Globus() }

    val musicUtil          by lazy { MusicUtil()    }
    val soundUtil          by lazy { SoundUtil()    }

    val disposableSet   = mutableSetOf<Disposable>()
    var backgroundColor = HelloMotoColor.blurik

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(Lodinga_HERA_Screen::class.java.name)
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