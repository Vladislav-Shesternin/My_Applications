package com.maxgames.stickwarl.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.maxgames.stickwarl.GameActivity
import com.maxgames.stickwarl.game.manager.*
import com.maxgames.stickwarl.game.manager.util.MusicUtil
import com.maxgames.stickwarl.game.manager.util.SoundUtil
import com.maxgames.stickwarl.game.manager.util.SpriteUtil
import com.maxgames.stickwarl.game.screens.LoadingScreen
import com.maxgames.stickwarl.game.utils.GColor
import com.maxgames.stickwarl.game.utils.advanced.AdvancedGame
import com.maxgames.stickwarl.game.utils.disposeAll
import com.maxgames.stickwarl.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val splash by lazy { SpriteUtil.Sapunaro() }
    val assets by lazy { SpriteUtil.NikolKidman() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

    var backgroundColor = GColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
    }

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("ViloVetaKa", MODE_PRIVATE)

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