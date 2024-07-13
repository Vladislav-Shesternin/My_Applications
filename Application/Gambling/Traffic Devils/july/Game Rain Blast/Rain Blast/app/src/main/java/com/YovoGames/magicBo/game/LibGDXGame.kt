package com.YovoGames.magicBo.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.YovoGames.magicBo.GameActivity
import com.YovoGames.magicBo.game.manager.*
import com.YovoGames.magicBo.game.manager.util.MusicUtil
import com.YovoGames.magicBo.game.manager.util.SoundUtil
import com.YovoGames.magicBo.game.manager.util.SpriteUtil
import com.YovoGames.magicBo.game.screens.LoadingScreen
import com.YovoGames.magicBo.game.utils.GColor
import com.YovoGames.magicBo.game.utils.advanced.AdvancedGame
import com.YovoGames.magicBo.game.utils.disposeAll
import com.YovoGames.magicBo.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val surgut  by lazy { SpriteUtil.GSfdsghdja() }
    val jakarta by lazy { SpriteUtil.JAshjms() }

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

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("ASUdisajDnma", MODE_PRIVATE)

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