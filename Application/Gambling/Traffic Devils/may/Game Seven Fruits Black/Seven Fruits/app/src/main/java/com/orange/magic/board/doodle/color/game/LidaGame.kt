package com.orange.magic.board.doodle.color.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.orange.magic.board.doodle.color.game.manager.*
import com.orange.magic.board.doodle.color.game.manager.util.MusicUtil
import com.orange.magic.board.doodle.color.game.manager.util.ParticleEffectUtil
import com.orange.magic.board.doodle.color.game.manager.util.SoundUtil
import com.orange.magic.board.doodle.color.game.manager.util.SpriteUtil
import com.orange.magic.board.doodle.color.game.screens.ALoderScreen
import com.orange.magic.board.doodle.color.game.utils.LidaColor
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedGame
import com.orange.magic.board.doodle.color.game.utils.disposeAll
import com.orange.magic.board.doodle.color.util.log

class LidaGame(val activity: com.orange.magic.board.doodle.color.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set

    val simagoche by lazy { SpriteUtil.Elias() }
    val dgop by lazy { SpriteUtil.Dgop() }

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val particleEffectUtil by lazy { ParticleEffectUtil() }


    var backgroundColor = LidaColor.fugi
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        activity.lottie.show()

        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        particleEffectManager = ParticleEffectManager(assetManager)

        navigationManager.navigate(ALoderScreen::class.java.name)
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