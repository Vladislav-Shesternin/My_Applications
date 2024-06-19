package com.dogbytegames.offtheroa.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.dogbytegames.offtheroa.GameActivity
import com.dogbytegames.offtheroa.game.manager.*
import com.dogbytegames.offtheroa.game.manager.util.MusicUtil
import com.dogbytegames.offtheroa.game.manager.util.SoundUtil
import com.dogbytegames.offtheroa.game.manager.util.SpriteUtil
import com.dogbytegames.offtheroa.game.screens.LoadingScreen
import com.dogbytegames.offtheroa.game.utils.GColor
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedGame
import com.dogbytegames.offtheroa.game.utils.disposeAll
import com.dogbytegames.offtheroa.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val aLdnr by lazy { SpriteUtil.Lida() }
    val aAlibaba by lazy { SpriteUtil.Allamigos() }

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

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("Voiletta", MODE_PRIVATE)

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